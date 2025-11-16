import { Routes } from '@angular/router';
import { Conference } from './components/conference/conference';
import { Keynote } from './components/keynote/keynote';

export const routes: Routes = [
  { path: '', redirectTo: '/conferences', pathMatch: 'full' },
  { path: 'conferences', component: Conference },
  { path: 'keynotes', component: Keynote }
];
