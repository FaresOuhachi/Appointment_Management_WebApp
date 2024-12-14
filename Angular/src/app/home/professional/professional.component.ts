import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-professional',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './professional.component.html',
  styleUrl: './professional.component.css',
})
export class ProfessionalComponent {}
