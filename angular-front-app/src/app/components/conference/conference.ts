import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ConferenceService } from '../../services/conference';
import { Conference } from '../../models/conference.model';

@Component({
  selector: 'app-conference',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './conference.html',
  styleUrl: './conference.component.css'
})
export class ConferenceComponent implements OnInit {
  conferences: Conference[] = [];
  newConference: Conference = {
    title: '',
    description: '',
    date: '',
    location: '',
    typeConference: ''
  };
  showForm = false;
  editingId: number | null = null;
  loading = false;
  error = '';

  constructor(private conferenceService: ConferenceService) {}

  ngOnInit(): void {
    this.loadConferences();
  }

  loadConferences(): void {
    this.loading = true;
    this.error = '';
    this.conferenceService.getAllConferences().subscribe({
      next: (data) => {
        this.conferences = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des conférences';
        console.error(err);
        this.loading = false;
      }
    });
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    if (!this.showForm) {
      this.resetForm();
    }
  }

  resetForm(): void {
    this.newConference = {
      title: '',
      description: '',
      date: '',
      location: '',
      typeConference: ''
    };
    this.editingId = null;
  }

  saveConference(): void {
    if (!this.newConference.title || !this.newConference.date) {
      this.error = 'Veuillez remplir les champs obligatoires';
      return;
    }

    if (this.editingId) {
      this.conferenceService.updateConference(this.editingId, this.newConference).subscribe({
        next: () => {
          this.loadConferences();
          this.resetForm();
          this.showForm = false;
          this.error = '';
        },
        error: (err) => {
          this.error = 'Erreur lors de la mise à jour';
          console.error(err);
        }
      });
    } else {
      this.conferenceService.createConference(this.newConference).subscribe({
        next: () => {
          this.loadConferences();
          this.resetForm();
          this.showForm = false;
          this.error = '';
        },
        error: (err) => {
          this.error = 'Erreur lors de la création';
          console.error(err);
        }
      });
    }
  }

  editConference(conference: Conference): void {
    this.newConference = { ...conference };
    this.editingId = conference.id || null;
    this.showForm = true;
  }

  deleteConference(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette conférence ?')) {
      this.conferenceService.deleteConference(id).subscribe({
        next: () => {
          this.loadConferences();
          this.error = '';
        },
        error: (err) => {
          this.error = 'Erreur lors de la suppression';
          console.error(err);
        }
      });
    }
  }
}
