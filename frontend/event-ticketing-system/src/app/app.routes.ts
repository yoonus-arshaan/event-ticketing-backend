import { Routes } from '@angular/router';
import { ConfigurationformComponent } from './pages/configurationform/configurationform.component';
import { LogdisplayComponent } from './pages/logdisplay/logdisplay.component';

export const routes: Routes = [
    {path: '', pathMatch: 'full', loadComponent: () => {return import('./pages/configurationform/configurationform.component').then(m => m.ConfigurationformComponent)}},
    {path: 'configuration', pathMatch: 'full', loadComponent: () => {return import('./pages/configurationform/configurationform.component').then(m => m.ConfigurationformComponent)}},
    {path: 'logs', pathMatch: 'full', loadComponent: () => {return import('./pages/logdisplay/logdisplay.component').then(m => m.LogdisplayComponent)}},
  

];
