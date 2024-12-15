import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DisponibilitesService } from '../../../services/disponibilites/disponibilite.service';
import { Rendezvous, RendezvousService } from '../../../services/rendezvous/rendezvous.service';
import { DatePipe, NgForOf, NgIf } from '@angular/common';
import { Utilisateur, UtilisateursService } from '../../../services/utilisateurs/utilisateurs.service';
import { RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';

interface Appointment {
  client: string;
  date: string;
  professional: string;
  status: string;
}

@Component({
  selector: 'app-rendez-vous',
  templateUrl: './rendezVous.component.html',
  standalone: true,
  imports: [
    RouterLink,
    ReactiveFormsModule,
    NgForOf,
    NgIf
  ],
  providers: [DatePipe],
  styleUrls: ['./rendezVous.component.css']
})

export class RendezVousComponent implements OnInit {
  appointmentForm!: FormGroup;
  professionals: Utilisateur[] = [];
  allSlots: any[] = [];
  filteredSlots: any[] = [];
  selectedProfessional: any = null;
  isSelected: boolean = false;
  loading: boolean = false;

  fixedClientId: number = 1;

  constructor(
    private fb: FormBuilder,
    private disponibilitesService: DisponibilitesService,
    private utilisateursService: UtilisateursService,
    private rendezvousService: RendezvousService,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.appointmentForm = this.fb.group({
      professional: ['', Validators.required]
    });

    this.fetchProfessionalsWithAvailabilities();

  }

  getProfessionalName(professionalId: number): string {
    const professional = this.professionals.find(prof => prof.id === professionalId);
    return professional ? professional.nom : 'Inconnu';

  }

  fetchProfessionalsWithAvailabilities(): void {
    this.loading = true;

    // Fetch all rendezvous to filter out the ones that have already been reserved
    this.rendezvousService.getAll().subscribe((rendezvousList) => {
      this.disponibilitesService.getAll().subscribe((disponibilites) => {
        // Get the list of unavailable disponibilites (those that have a rendezvous with any status other than 'ANNULE')
        const unavailableDisponibilitesIds = new Set(
          rendezvousList
            .filter((rendezvous) => rendezvous.statut !== 'ANNULE')
            .map((rendezvous) => rendezvous.disponibiliteId)
        );

        // Filter out the disponibilites that are already reserved or those with rendezvous that are not 'ANNULE'
        this.allSlots = disponibilites.filter(
          (disponibilite) => !unavailableDisponibilitesIds.has(disponibilite.id)
        );

        // Initialize the filteredSlots with all available slots
        this.filteredSlots = [...this.allSlots];

        // Now fetch professionals based on the available slots
        const observables = this.allSlots.map((slot) =>
          this.utilisateursService.getById(slot.professionalId)
        );

        forkJoin(observables).subscribe((professionals) => {
          const uniqueProfessionals = new Set<number>();
          this.professionals = [];

          professionals.forEach((professional) => {
            if (professional.role === 'PROFESSIONNEL' && !uniqueProfessionals.has(professional.id)) {
              uniqueProfessionals.add(professional.id);
              this.professionals.push(professional);
            }
          });

          this.loading = false;
        }, (error) => {
          this.loading = false;
          console.error('Error fetching professionals:', error);
        });
      }, (error) => {
        this.loading = false;
        console.error('Error fetching disponibilites:', error);
      });
    });
  }

  onProfessionalChange(event: any): void {
    // Extract the professional ID from the event target value
    this.isSelected = true;
    const professionalId = event.target.value.split(':')[1].trim();
    // console.log('Event Target object: ', event.target);
    // console.log('Selected professional ID:', professionalId);

    // Find the selected professional by comparing the IDs
    this.selectedProfessional = this.professionals.find(prof => prof.id === parseInt(professionalId));
    // console.log('Selected professional:', this.selectedProfessional);

    // Filter the slots based on the selected professional's ID
    this.filteredSlots = this.allSlots.filter(slot => slot.professionalId === parseInt(professionalId));
    // console.log('Filtered slots:', this.filteredSlots);
  }

  formatDate(date: string): string {
    return <string>this.datePipe.transform(new Date(date), 'EEEE, dd-MM-yyyy');
  }

  formatDateTime(dateDebut: string, dateFin: string): string {
    if (!dateDebut || !dateFin) {
      console.error('Invalid dateDebut or dateFin values');
      return '';
    }

    const formattedStartDate = this.datePipe.transform(new Date(dateDebut), 'HH:mm');
    const formattedEndDate = this.datePipe.transform(new Date(dateFin), 'HH:mm');
    return `${formattedStartDate} - ${formattedEndDate}`;
  }

  resetFilter(): void {
    this.appointmentForm.reset();
    this.isSelected = false;
    this.selectedProfessional = null;
    this.filteredSlots = this.allSlots;
  }

  reserveSlot(slot: any): void {
    console.log('Reserving slot:', slot);
    // Handle the slot reservation here
    const rendezvous: Rendezvous = {
      id: 0,
      clientId: this.fixedClientId,
      professionalId: slot.professionalId,
      disponibiliteId: slot.id,
      statut: 'EN_ATTENTE'
    };

    this.rendezvousService.create(rendezvous).subscribe((response) => {
      console.log('Rendezvous created successfully:', response);
      this.fetchProfessionalsWithAvailabilities();
    }, (error) => {
      console.error('Error creating rendezvous:', error);
    });

  }
}
