import { Component, OnInit } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent implements OnInit {
  title = 'Conference & Keynote Management';
  isLoggedIn = false;
  username = '';

  constructor(private keycloak: KeycloakService) {}

  ngOnInit(): void {
    this.isLoggedIn = this.keycloak.isUserLoggedIn();
    if (this.isLoggedIn) {
      this.username = this.keycloak.getUsername();
    }
  }

  logout(): void {
    this.keycloak.logout();
  }
}

