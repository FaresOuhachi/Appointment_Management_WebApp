import { Component, signal } from '@angular/core';
import { HlmButtonDirective } from '@spartan-ng/ui-button-helm';
import { Router } from '@angular/router';  // Import Router for navigation

import {
  HlmCardContentDirective, HlmCardDescriptionDirective,
  HlmCardDirective,
  HlmCardFooterDirective,
  HlmCardHeaderDirective,
  HlmCardTitleDirective
} from '@spartan-ng/ui-card-helm';
import { HlmInputDirective } from '@spartan-ng/ui-input-helm';
import { HlmLabelDirective } from '@spartan-ng/ui-label-helm';
import {
  BrnPopoverComponent,
  BrnPopoverContentDirective,
  BrnPopoverTriggerDirective
} from '@spartan-ng/ui-popover-brain';
import { HlmIconComponent } from '@spartan-ng/ui-icon-helm';
import { HlmPopoverContentDirective } from '@spartan-ng/ui-popover-helm';
import { provideIcons } from '@ng-icons/core';
import { lucideCheck, lucideChevronDown } from '@ng-icons/lucide';
import { HlmCommandImports } from '@spartan-ng/ui-command-helm';
import { BrnCommandImports } from '@spartan-ng/ui-command-brain';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Utilisateur, UtilisateursService } from '../services/utilisateurs/utilisateurs.service';
import { NgForOf, NgIf } from '@angular/common';

type Role = { label: string; value: string };

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    BrnCommandImports,
    HlmCommandImports,
    HlmIconComponent,
    BrnPopoverComponent,
    BrnPopoverTriggerDirective,
    BrnPopoverContentDirective,
    HlmPopoverContentDirective,
    HlmCardDirective,
    HlmCardHeaderDirective,
    HlmCardTitleDirective,
    HlmCardDescriptionDirective,
    HlmCardContentDirective,
    HlmLabelDirective,
    HlmInputDirective,
    HlmCardFooterDirective,
    HlmButtonDirective,
    RouterLink,
    FormsModule,
    NgIf,
    NgForOf
  ],
  providers: [provideIcons({ lucideCheck, lucideChevronDown })],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  public roles: Role[] = [
    { label: 'Client', value: 'CLIENT' },
    { label: 'Professional', value: 'PROFESSIONNEL' }
  ];

  selectedRole: Role | null = null;
  dropdownOpen = false;

  fullName = '';
  email = '';
  password = '';

  constructor(
    private utilisateursService: UtilisateursService,
    private router: Router  // Inject Router
  ) { }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  selectRole(role: Role) {
    this.selectedRole = role;
    this.dropdownOpen = false;
  }

  register() {
    if (!this.fullName || !this.email || !this.password || !this.selectedRole) {
      alert('Please fill out all fields and select a role.');
      return;
    }

    const utilisateur: Utilisateur = {
      id: 0,  // The backend should assign an ID
      nom: this.fullName,
      email: this.email,
      motDePasse: this.password,
      role: this.selectedRole.value
    };

    this.utilisateursService.create(utilisateur).subscribe({
      next: (createdUser: Utilisateur) => {
        alert(`User registered successfully: ${createdUser.nom}`);
        // Pass the whole user object in the state
        this.router.navigate(['/home'], {
          state: { user: createdUser }
        }).then(r => console.log(r));
      },
      error: (err: any) => {
        console.error(err);
        alert('Failed to register user. Please try again.');
      }
    });
  }
}
