import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Configuration } from '../model/configuration.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  apiServerUrl = 'http://localhost:8080/api/configuration'; // Add 'http://' protocol
  http = inject(HttpClient);
  constructor() { }

  
  public saveConfiguration(configuration: Configuration): Observable<Configuration> {
    return this.http.post<Configuration>(`${this.apiServerUrl}/save`, configuration)
  }

  public getConfiguration(): Observable<Configuration> {
    return this.http.get<Configuration>(`${this.apiServerUrl}/get`);
  }


  startTicketSystem(): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/start-system`, {});
  }

  // Stop the ticketing system
  stopTicketSystem(): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/stop-system`, {});
  }

  // Reset the ticketing system
  resetTicketSystem(): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/reset-system`, {});
  }

  // Fetch system logs (ticket sales, system changes, etc.)
  getLogs(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiServerUrl}/logs`);
  }
}
