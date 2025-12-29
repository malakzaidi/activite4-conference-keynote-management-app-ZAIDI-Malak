export interface Conference {
  id?: string;
  titre: string;
  type: string;
  date: string;
  dureeMinutes: number;
  nombreInscrits: number;
  score?: number;
  keynoteId?: string;
  keynote?: any;
  reviews?: any[];
}

export interface Keynote {
  id?: string;
  nom: string;
  prenom: string;
  email: string;
  fonction: string;
}
