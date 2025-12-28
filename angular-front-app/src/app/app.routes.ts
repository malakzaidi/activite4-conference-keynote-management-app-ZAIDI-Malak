import { Routes } from '@angular/router';
import {ConferenceComponent} from './components/conference/conference';
import {KeynoteComponent} from './components/keynote/keynote';



export const routes: Routes = [
  { path: '', redirectTo: '/conferences', pathMatch: 'full' },
  { path: 'conferences', component: ConferenceComponent },
  { path: 'keynotes', component: KeynoteComponent }
];
