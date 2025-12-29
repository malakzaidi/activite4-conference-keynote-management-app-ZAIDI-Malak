import { Routes } from '@angular/router';
import { ConferenceComponent } from './components/conference/conference';
import { KeynoteComponent } from './components/keynote/keynote';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/conferences', pathMatch: 'full' },
  { path: 'conferences', component: ConferenceComponent, canActivate: [AuthGuard] },
  { path: 'keynotes', component: KeynoteComponent, canActivate: [AuthGuard] }
];
