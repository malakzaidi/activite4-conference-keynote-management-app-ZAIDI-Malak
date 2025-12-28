import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { KeynoteService } from '../../services/keynote';
import { Keynote } from '../../models/keynote.model';

@Component({
  selector: 'app-keynote',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './keynote.html',
  styleUrl: './keynote.component.css'
})
export class KeynoteComponent implements OnInit {
  keynotes: Keynote[] = [];
  newKeynote: Keynote = {
    title: '',
    speaker: '',
    duration: 0,
    description: '',
    conferenceId: undefined
  };
  showForm = false;
  editingId: number | null = null;
  loading = false;
  error = '';

  constructor(private keynoteService: KeynoteService) {}

  ngOnInit(): void {
    this.loadKeynotes();
  }

  loadKeynotes(): void {
    this.loading = true;
    this.error = '';
    this.keynoteService.getAllKeynotes().subscribe({
      next: (data) => {
        this.keynotes = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des keynotes';
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
    this.newKeynote = {
      title: '',
      speaker: '',
      duration: 0,
      description: '',
      conferenceId: undefined
    };
    this.editingId = null;
  }

  saveKeynote(): void {
    if (!this.newKeynote.title || !this.newKeynote.speaker) {
      this.error = 'Veuillez remplir les champs obligatoires';
      return;
    }

    if (this.editingId) {
      this.keynoteService.updateKeynote(this.editingId, this.newKeynote).subscribe({
        next: () => {
          this.loadKeynotes();
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
      this.keynoteService.createKeynote(this.newKeynote).subscribe({
        next: () => {
          this.loadKeynotes();
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

  editKeynote(keynote: Keynote): void {
    this.newKeynote = { ...keynote };
    this.editingId = keynote.id || null;
    this.showForm = true;
  }

  deleteKeynote(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette keynote ?')) {
      this.keynoteService.deleteKeynote(id).subscribe({
        next: () => {
          this.loadKeynotes();
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
