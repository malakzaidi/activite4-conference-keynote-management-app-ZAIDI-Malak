import { KeycloakService } from 'keycloak-angular';
import { keycloakConfig } from './keycloak.config';

export function initializeKeycloak(keycloak: KeycloakService): () => Promise<boolean> {
  return () => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: {
            url: keycloakConfig.url,
            realm: keycloakConfig.realm,
            clientId: keycloakConfig.clientId,
          },
          initOptions: {
            onLoad: 'login-required', // Force la connexion
            silentCheckSsoRedirectUri:
              window.location.origin + '/assets/silent-check-sso.html',
          },
          enableBearerInterceptor: true,
          bearerPrefix: 'Bearer',
          bearerExcludedUrls: ['/assets', '/clients/public'],
        });
        resolve(true);
      } catch (error) {
        reject(error);
      }
    });
  };
}
