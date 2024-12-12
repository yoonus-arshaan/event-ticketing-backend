import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private apiUrl = '/api/tickets/availability/log';

  constructor(private http: HttpClient) {}

  getTicketAvailabilityLog(): Observable<string> {
    return this.http.get<string>(this.apiUrl);
  }
}
