import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private keycloak: KeycloakService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    if (this.keycloak.isUserLoggedIn()) {
      if (this.isTokenExpired()) {
        return this.keycloak.login();
      }
      return true;
    }
    return this.keycloak.login();
  }

  private isTokenExpired(): boolean {
    const token = this.keycloak.getToken();
    if (!token) return true;

    const decoded: any = this.parseToken(token);
    const expiryTime = decoded.exp * 1000;
    return expiryTime < Date.now();
  }

  private parseToken(token: string): any {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map((c) => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
        .join('')
    );
    return JSON.parse(jsonPayload);
  }
}
