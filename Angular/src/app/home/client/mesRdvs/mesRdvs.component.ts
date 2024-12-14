import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { RendezvousService } from '../../../services/rendezvous/rendezvous.service';
import { UtilisateursService } from '../../../services/utilisateurs/utilisateurs.service';
import { DisponibilitesService } from '../../../services/disponibilites/disponibilite.service';
import { forkJoin, Subject } from 'rxjs';
import { map, takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-mesrendezvous',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './mesRdvs.component.html',
  styleUrls: ['./mesRdvs.component.css']
})
export class MesrendezvousComponent implements OnInit, OnDestroy {
  appointmentForm: FormGroup;
  appointments: any[] = [];
  filteredAppointments: any[] = [];
  professionals: any[] = [];
  clientId = 4; // Fixed clientId for filtering

  pro = 0;
  date = '';
  private destroy$ = new Subject<void>();

  constructor(
    private rendezvousService: RendezvousService,
    private utilisateursService: UtilisateursService,
    private disponibiliteService: DisponibilitesService,
    private fb: FormBuilder
  ) {
    this.appointmentForm = this.fb.group({
      professional: [''],
      date: ['']
    });
  }

  ngOnInit(): void {
    this.loadClientRendezvous();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadProfessionals(): void {
    this.utilisateursService.getProfessionals().pipe(takeUntil(this.destroy$)).subscribe({
      next: (professionals) => {
        this.professionals = professionals;
      },
      error: (err) => console.error('Error fetching professionals:', err)
    });
  }

  loadClientRendezvous(): void {
    this.rendezvousService.getByClientId(this.clientId).pipe(takeUntil(this.destroy$)).subscribe({
      next: (appointments) => {
        const enrichedAppointments$ = appointments.map((appointment) =>
          forkJoin({
            professional: this.utilisateursService.getById(appointment.professionalId),
            disponibilite: this.disponibiliteService.getById(appointment.disponibiliteId)
          }).pipe(
            map(({ professional, disponibilite }) => {
              const startTime = disponibilite.dateDebut.split('T')[1].substring(0, 5); // Extract "HH:mm"
              const endTime = disponibilite.dateFin.split('T')[1].substring(0, 5); // Extract "HH:mm"
              return {
                ...appointment,
                professional: professional.nom,
                disponibiliteDate: disponibilite.dateDebut.split('T')[0], // Use dateDebut or dateFin
                disponibiliteHeure: `${startTime} - ${endTime}` // Format time range
              };
            })
          )
        );

        forkJoin(enrichedAppointments$).pipe(takeUntil(this.destroy$)).subscribe({
          next: (result) => {
            this.appointments = result;
            this.filteredAppointments = [...this.appointments]; // Initialize filtered list
            this.loadProfessionals(); // Now we load the professionals
            console.log('Client rendezvous:', this.appointments);
          },
          error: (err) => console.error('Error enriching appointments:', err)
        });
      },
      error: (err) => console.error('Error fetching client rendezvous:', err)
    });
  }

  applyFilters(): void {

    this.filteredAppointments = this.appointments.filter((appointment) => {
      const matchesProfessional = this.pro ? appointment.professionalId === this.pro : true;
      console.log('matchesProfessional:', appointment);
      const matchesDate = this.date ? appointment.disponibiliteDate === this.date : true;
      return matchesProfessional && matchesDate;
    });
  }


  onProChange(event: any): void {
    console.log(event.target.value);
    this.pro = Number(event.target.value);
    this.applyFilters();

  }


  onDateChange(event: any): void {
    this.date = event.target.value;
    console.log(event.target.value);
    this.applyFilters();

  }

  removeRdv(id: number): void {
    this.rendezvousService.delete(id).pipe(takeUntil(this.destroy$)).subscribe({
      next: () => {
        this.loadClientRendezvous();
      },
      error: (err) => console.error('Error deleting rendezvous:', err)
    });
  }

  getStatutClass(statut: string): string {
    switch (statut) {
      case 'LIBRE':
        return 'text-blue-500'; // Blue for "LIBRE"
      case 'EN_ATTENTE':
        return 'text-yellow-500'; // Yellow for "EN_ATTENTE"
      case 'CONFIRME':
        return 'text-green-500'; // Green for "CONFIRME"
      case 'ANNULE':
        return 'text-red-500'; // Red for "ANNULE"
      default:
        return 'text-gray-500'; // Default color for unknown statuses
    }
  }

  formatStatut(statut: string): string {
    const formattedStatut = statut
      .toLowerCase()
      .replace('_', ' ') // Replace underscores with spaces
      .split(' ')        // Split words into an array
      .map((word) => word.charAt(0).toUpperCase() + word.slice(1)) // Capitalize each word
      .join(' ');        // Join words back together
    return formattedStatut;
  }
}
