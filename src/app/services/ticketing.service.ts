import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TicketingService {
  private baseUrl = 'http://localhost:8080/api/ticketing';

  constructor(private http: HttpClient) {}

  // Start the system
  startSystem(config: any): Observable<string> {
    const params = new HttpParams()
      .set('totalTickets', config.totalTickets)
      .set('releaseRate', config.releaseRate)
      .set('purchaseRate', config.purchaseRate)
      .set('vendorCount', config.vendorCount)
      .set('customerCount', config.customerCount)
      .set('maxCapacity', config.maxCapacity);

    return this.http.post<string>(`${this.baseUrl}/start`, null, { params });
  }

  // Get system status
  getStatus(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/status`);
  }

  // Stop the system
  stopSystem(): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/stop`, null);
  }

  // Fetch logs
  getLogs(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/logs`);
  }
}
