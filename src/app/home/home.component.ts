import { Component, OnInit, OnDestroy } from '@angular/core';
import { TicketingService } from '../services/ticketing.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LogDisplayComponent } from '../components/log-display/log-display.component';
import { HeaderComponent } from '../components/header/header.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    LogDisplayComponent,
    HeaderComponent
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit, OnDestroy {
  totalTickets: number = 0;
  releaseRate: number = 0;
  purchaseRate: number = 0;
  vendorCount: number = 0;
  customerCount: number = 0;
  maxCapacity: number = 0;
  logs: string[] = [];
  ticketPoolSize: number = 0; // Available ticket count
  private logPollingInterval: any; // Reference for the polling interval

  constructor(private ticketingService: TicketingService) {}

  ngOnInit(): void {
    // Start polling for logs every 2 seconds
    this.logPollingInterval = setInterval(() => this.fetchLogs(), 2000);
  }

  /**
   * Fetch logs from the backend
   */
  fetchLogs(): void {
    this.ticketingService.getLogs().subscribe({
      next: (fetchedLogs: string[]) => {
        this.logs = fetchedLogs; // Update logs from the backend
      },
      error: (err) => {
        console.error('Error fetching logs:', err);
        this.logs.push('Error fetching logs: ' + (err.error?.message || 'Unknown error'));
      },
    });
  }

  /**
   * Start the ticketing system with the configured parameters
   */
  startSystem(): void {
    const config = {
      totalTickets: this.totalTickets,
      releaseRate: this.releaseRate,
      purchaseRate: this.purchaseRate,
      vendorCount: this.vendorCount,
      customerCount: this.customerCount,
      maxCapacity: this.maxCapacity,
    };

    // Validate inputs before sending the request
    if (Object.values(config).some((value) => value <= 0)) {
      this.logs.push('Error: All input values must be positive numbers.');
      return;
    }

    this.ticketingService.startSystem(config).subscribe({
      next: (response: string) => {
        console.log('System started successfully:', response);
        this.logs.push(`System started successfully: ${response}`);
      },
      error: (err) => {
        console.error('System start error:', err);
        this.logs.push('Error starting system: ' + (err.error?.message || 'Unknown error'));
      },
    });
  }

  /**
   * Fetch the current system status
   */
  getStatus(): void {
    this.ticketingService.getStatus().subscribe({
      next: (status: { availableTickets: number; message: string }) => {
        console.log('System status:', status);
        this.logs.push(`System Status: ${status.message}`);
        this.ticketPoolSize = status.availableTickets; // Update available ticket count
      },
      error: (err) => {
        console.error('Error fetching system status:', err);
        this.logs.push('Error fetching status: ' + (err.error?.message || 'Unknown error'));
      },
    });
  }

  /**
   * Stop the ticketing system
   */
  stopSystem(): void {
    this.ticketingService.stopSystem().subscribe({
      next: (response: string) => {
        console.log('System stopped successfully:', response);
        this.logs.push(`System stopped successfully: ${response}`);
      },
      error: (err) => {
        console.error('Error stopping system:', err);
        this.logs.push('Error stopping system: ' + (err.error?.message || 'Unknown error'));
      },
    });
  }

  /**
   * Clean up the polling interval
   */
  ngOnDestroy(): void {
    if (this.logPollingInterval) {
      clearInterval(this.logPollingInterval);
      this.logPollingInterval = null;
    }
  }
}
