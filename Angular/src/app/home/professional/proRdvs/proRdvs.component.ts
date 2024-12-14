import { Component, OnDestroy, OnInit } from '@angular/core';
import { RendezvousService } from '../../../services/rendezvous/rendezvous.service';
import { CommonModule, DatePipe } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Subject, forkJoin } from 'rxjs';
import { UtilisateursService } from '../../../services/utilisateurs/utilisateurs.service';
import { DisponibilitesService } from '../../../services/disponibilites/disponibilite.service';
import { catchError, map } from 'rxjs/operators';

@Component({
  selector: 'app-pro-rdvs',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  providers: [DatePipe],
  templateUrl: './proRdvs.component.html',
  styleUrls: ['./proRdvs.component.css']
})
export class ProRdvsComponent implements OnInit, OnDestroy {
  MesRendezVous: any[] = []; // List of appointments with client and disponibilite info
  destroy$ = new Subject<void>();
  professionalId = 2; // Fixed professionalId for filtering

  constructor(
    private rendezvousService: RendezvousService,
    private utilisateursService: UtilisateursService,
    private disponibilitesService: DisponibilitesService,
    private datePipe: DatePipe // Add DatePipe here

  ) {}

  ngOnInit(): void {
    this.fetchMesRendezVous();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  fetchMesRendezVous(): void {
    // Fetch appointments for the professional
    this.rendezvousService.getByProfessionalId(this.professionalId).subscribe({
      next: (appointments) => {
        // Create an array of observables for fetching the client and disponibilite details for each appointment
        const appointmentsWithDetails$ = appointments.map((appointment) => {
          const client$ = this.utilisateursService.getById(appointment.clientId).pipe(
            catchError((err) => {
              console.error('Error fetching client:', err);
              return [null]; // Handle error by returning a default value
            })
          );
          const disponibilite$ = this.disponibilitesService.getById(appointment.disponibiliteId).pipe(
            catchError((err) => {
              console.error('Error fetching disponibilite:', err);
              return [null]; // Handle error by returning a default value
            })
          );

          return forkJoin([client$, disponibilite$]).pipe(
            // Merge the responses into a single object
            map(([client, disponibilite]) => {
              return { ...appointment, client, disponibilite };
            })
          );
        });

        // Use forkJoin to wait until all API calls have completed
        forkJoin(appointmentsWithDetails$).subscribe({
          next: (appointmentsWithDetails) => {
            // Now the appointments have client and disponibilite data
            this.MesRendezVous = appointmentsWithDetails.filter(rdv => rdv.client && rdv.disponibilite); // Only keep valid appointments
            console.log('Appointments with details:', this.MesRendezVous);
          },
          error: (err) => console.error('Error fetching appointments with details:', err)
        });
      },
      error: (err) => console.error('Error fetching appointments:', err)
    });
  }

  updateRdvStatut(appointmentId: number, statut: string): void {
    // First, we need to find the rendezvous by ID
    this.rendezvousService.getById(appointmentId).subscribe((appointment) => {
      if (appointment) {
        // Update the statut based on the button clicked
        appointment.statut = statut;

        // Now send the updated rendezvous status back to the server
        this.rendezvousService.update(appointmentId, appointment).subscribe(
          (updatedAppointment) => {
            // Update the local list to reflect the changes
            this.MesRendezVous = this.MesRendezVous.map((rdv) =>
              rdv.id === updatedAppointment.id ? { ...rdv, statut: updatedAppointment.statut } : rdv
            );
          },
          (error) => {
            console.error('Error updating rendezvous status:', error);
          }
        );
      }
    });
  }


  getStatutClass(statut: string): string {
    switch (statut) {
      case 'CONFIRME':
        return 'text-green-500';
      case 'ANNULE':
        return 'text-red-500';
      default:
        return 'text-yellow-500'; // For "EN_ATTENTE"
    }
  }

  formatStatut(statut: string): string {
    switch (statut) {
      case 'CONFIRME':
        return 'Confirmé';
      case 'ANNULE':
        return 'Annulé';
      default:
        return 'En Attente';
    }
  }

  formatDate(date: string): string {
    return <string>this.datePipe.transform(new Date(date), 'EEEE, dd-MM-yyyy');
  }

  formatTime(dateDebut: string, dateFin: string): string {
    if (!dateDebut || !dateFin) {
      console.error('Invalid dateDebut or dateFin values');
      return '';
    }

    const formattedStartDate = this.datePipe.transform(new Date(dateDebut), 'HH:mm');
    const formattedEndDate = this.datePipe.transform(new Date(dateFin), 'HH:mm');
    return `${formattedStartDate} - ${formattedEndDate}`;
  }

}
