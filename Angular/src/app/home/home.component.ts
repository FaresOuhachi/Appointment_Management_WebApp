import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DisponibiliteComponent } from './professional/disponibilite/disponibilite.component';
import { RendezVousComponent } from './client/rendezVous/rendezVous.component';
import { ClientComponent } from './client/client.component';
import { ProfessionalComponent } from './professional/professional.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, DisponibiliteComponent, RendezVousComponent, ClientComponent, ProfessionalComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  username: string = 'Fares';
  role: string = 'client';
  logout() {
    // if role is 'professional' set it to client, if not, set it to 'professional'
    this.role = this.role === 'professional' ? 'client' : 'professional';
  }
}
