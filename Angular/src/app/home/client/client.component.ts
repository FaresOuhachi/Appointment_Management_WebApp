import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css',
})
export class ClientComponent {}
