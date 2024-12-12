import { Component, inject, OnInit, signal } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { FormsModule, NgForm } from '@angular/forms';
import { ConfigurationService } from '../../services/configuration.service';
import { Configuration } from '../../model/configuration.type';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-configurationform',
  standalone: true,
  imports: [HeaderComponent, FormsModule, CommonModule],
  templateUrl: './configurationform.component.html',
  styleUrl: './configurationform.component.css'
})
export class ConfigurationformComponent implements OnInit{
  configurationService = inject(ConfigurationService);
  configuration = signal<Configuration>({} as Configuration);
  
  ngOnInit(): void {

    this.configurationService.getConfiguration().subscribe({
      next: (response: Configuration) => {
        console.log('Configuration retrieved successfully', response)
        this.configuration.set(response);
      },
      error: (error: HttpErrorResponse) => {
        console.log('Failed to retrieve configuration')
        // alert('Failed to retriieve configuration: ' + error.message);
      }
    });

  }

  public onSubmit(configurationForm: NgForm): void {
    document.getElementById('configuration-form')?.click();

    this.configurationService.saveConfiguration(configurationForm.value).subscribe(
      (response: Configuration) => {
        console.log('Configuration saved successfully:', response);
        configurationForm.reset();
      },
      (error: HttpErrorResponse) => {
        // alert('Failed to save configuration: ' + error.message)
        console.error('Error saving configuration:', error);
        configurationForm.reset();
      }
    );

  }

}
