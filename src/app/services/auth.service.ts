import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, of, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private router: Router) {} // Inject Router here

  private validateFields(credentials: { username: string; password: string; role: string }): boolean {
    return (
      !!credentials.username && !!credentials.password && !!credentials.role
    );
  }

  // Login user method
  login(credentials: { username: string; password: string; role: string }): Observable<string> {
    if (this.validateFields(credentials)) {
      // Navigate to the desired route (e.g., "/dashboard") upon successful login
      this.router.navigate(['/dashboard']);
      return of('Login successful');
    } else {
      return throwError(() => new Error('All fields are required!'));
    }
  }

  // Register user method
  register(user: { username: string; password: string; role: string }): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/register`, user).pipe(
      catchError((error) => {
        console.error('Registration failed:', error);
        return throwError(() => new Error(error.error || 'Registration failed'));
      })
    );
  }
}