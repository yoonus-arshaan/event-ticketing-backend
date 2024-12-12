import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LogService {

  private apiUrl = 'http://localhost:8080/api/logs';  // Adjust the URL based on your API endpoint

  constructor(private http: HttpClient) { }

  // Method to get logs
  getLogs(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl);  // Assuming the logs are returned as an array of strings
  }

  // Method to clear logs if needed (optional)
  clearLogs(): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/clear`);
  }
}
