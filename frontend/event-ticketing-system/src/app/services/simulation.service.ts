import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SimulationService {

  private apiUrl = '/api/simulation';

  constructor(private http: HttpClient) {}

  // Start simulation
  startSimulation(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/start`, {});
  }

  // Stop simulation
  stopSimulation(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/stop`, {});
  }

  // Reset simulation
  resetSimulation(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/reset`, {});
  }
}
