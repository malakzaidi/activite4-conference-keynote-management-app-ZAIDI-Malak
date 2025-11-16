import { Keynote } from './keynote.model';

export interface Conference {
  id: string;
  titre: string;
  type: string;
  date: string;
  dureeMinutes: number;
  nombreInscrits: number;
  score: number;
  keynoteId: string;
  keynote: Keynote;
  reviews: any[];
}
