import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Disponibilite, DisponibilitesService } from '../../../services/disponibilites/disponibilite.service';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe, NgClass, NgForOf, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-disponibilite',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule, DatePipe, RouterLink, NgForOf, NgIf, NgClass],
  providers: [DisponibilitesService],
  templateUrl: './disponibilite.component.html',
  styleUrls: ['./disponibilite.component.css']
})
export class DisponibiliteComponent implements OnInit {
  disponibilites: Disponibilite[] = [];
  disponibiliteForm: FormGroup;
  professionalId = 2;  // Set this to the actual professionalId dynamically as required
  formSubmitted = false; // Track if the form has been submitted

  constructor(
    private disponibilitesService: DisponibilitesService,
    private fb: FormBuilder
  ) {
    this.disponibiliteForm = this.fb.group({
      dateDebut: ['', Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required],
      professionalId: [this.professionalId]  // Use the dynamic professionalId
    });
  }

  ngOnInit(): void {
    this.fetchDisponibilites();  // Fetch disponibilites for a specific professionalId
  }

  // Fetch disponibilites by professionalId
  fetchDisponibilites(): void {
    this.disponibilitesService.getByProfessionalId(this.professionalId).subscribe(
      (data) => {

        this.disponibilites = data;
      },
      (error) => {
        console.error('Error fetching disponibilites:', error);
      }
    );
  }

  // Check if the form is valid and the end time is greater than start time
  isTimeValid(): boolean {
    const startTime = this.disponibiliteForm.get('startTime')?.value;
    const endTime = this.disponibiliteForm.get('endTime')?.value;
    const dateDebut = this.disponibiliteForm.get('dateDebut')?.value;

    // Combine the date and time into Date objects for comparison
    const startDateTime = this.combineDateAndTime(dateDebut, startTime);
    const endDateTime = this.combineDateAndTime(dateDebut, endTime);

    // Return true if end time is greater than start time
    return new Date(startDateTime) < new Date(endDateTime);
  }

  // Add new disponibilite
  addDisponibilite(): void {
    this.formSubmitted = true; // Mark form as submitted
    const startTime = this.disponibiliteForm.get('startTime')?.value;
    const endTime = this.disponibiliteForm.get('endTime')?.value;
    const dateDebut = this.disponibiliteForm.get('dateDebut')?.value;

    // Combine the date and time into Date objects for comparison
    const startDateTime = this.combineDateAndTime(dateDebut, startTime);
    const endDateTime = this.combineDateAndTime(dateDebut, endTime);

    // Proceed with adding the disponibilite only if the form is valid and times are correct
    if (this.isTimeValid() && this.disponibiliteForm.valid) {
      const formValues = this.disponibiliteForm.value;

      // Prepare the object to send to the backend
      const newDisponibilite: Disponibilite = {
        ...formValues,
        dateDebut: startDateTime,
        dateFin: endDateTime,
      };

      this.disponibilitesService.create(newDisponibilite).subscribe(
        (createdDisponibilite) => {
          this.disponibilites.push(createdDisponibilite); // Update list
          this.disponibiliteForm.reset({
            dateDebut: '',
            startTime: '',
            endTime: '',
            professionalId: this.professionalId // Retain professionalId
          }); // Reset form after successful submission
          this.formSubmitted = false; // Reset submission flag
        },
        (error) => {
          console.error('Error adding disponibilite:', error);
        }
      );
    }
  }


  // Helper function to combine date and time
  private combineDateAndTime(date: string, time: string): string {
    return `${date}T${time}`; // Returns a combined string in "YYYY-MM-DDTHH:mm" format
  }

  // Remove disponibilite
  removeDisponibilite(id: number): void {
    this.disponibilitesService.delete(id).subscribe(
      () => {
        this.disponibilites = this.disponibilites.filter(d => d.id !== id); // Remove from list
      },
      (error) => {
        console.error('Error removing disponibilite:', error);
      }
    );
  }
}

