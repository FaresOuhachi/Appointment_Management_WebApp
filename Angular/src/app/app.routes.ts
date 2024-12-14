import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { DisponibiliteComponent } from './home/professional/disponibilite/disponibilite.component';
import { RendezVousComponent } from './home/client/rendezVous/rendezVous.component';
import { ProfessionalComponent } from './home/professional/professional.component';
import { ClientComponent } from './home/client/client.component';
import { MesrendezvousComponent } from './home/client/mesRdvs/mesRdvs.component';
import { ProRdvsComponent } from './home/professional/proRdvs/proRdvs.component';


export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  // { path: '', redirectTo: 'login', pathMatch: 'full' }, // Default route
  { path: '', redirectTo: 'home', pathMatch: 'full' }, // Default route
  { path: 'disponibilite', component: DisponibiliteComponent}, // professional sub-root
  { path: 'rendezvous' , component: RendezVousComponent}, // professional sub-root
  { path: 'professional', component: ProfessionalComponent}, // professional root
  { path: 'client', component: ClientComponent},
  { path: 'mes-rendezvous', component: MesrendezvousComponent},
  { path: 'pro-rendezvous', component: ProRdvsComponent}
];
