import { Component, inject, OnInit, signal } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { LogService } from '../../services/log.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-logdisplay',
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './logdisplay.component.html',
  styleUrl: './logdisplay.component.css'
})
export class LogdisplayComponent implements OnInit {
  logService = inject(LogService);

  constructor(logService: LogService) {}

  logs = signal<String[]>([]);

  ngOnInit(): void {
    this.loadLogs();
  }

  loadLogs(): void {
    this.logService.getLogs().subscribe({
      next: (logs: string[]) => {
        this.logs.set(logs);
      },
      error: (err) => {
        console.error('Failed to load logs', err);
      }
    });
  }
  
  clearLogs(): void {
    this.logService.clearLogs().subscribe({
      next: () => {
        this.logs.set([]);  // Clear the logs locally
      },
      error: (err) => {
        console.error('Failed to clear logs', err);
      }
    });
  }
}
