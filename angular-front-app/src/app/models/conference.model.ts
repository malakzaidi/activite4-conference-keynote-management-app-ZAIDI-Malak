export interface Conference {
  id?: number;
  title: string;
  description: string;
  date: string;
  location: string;
  typeConference: string;
  keynotes?: Keynote[];
}

export interface Keynote {
  id?: number;
  title: string;
  speaker: string;
  duration: number;
  description: string;
  conferenceId?: number;
}
