import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { HeaderComponent } from "../header/header.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username = ''; // Holds the entered username
  password = ''; // Holds the entered password
  role = 'customer'; // Default role

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    if (!this.username || !this.password || !this.role) {
      console.error('Login failed: All fields must be filled');
      alert('Please fill in all fields');
      return;
    }

    const user = {
      username: this.username,
      password: this.password,
      role: this.role,
    };

    this.authService.login(user).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        alert('Login successful');
        // Navigate to the home page on successful login
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Login failed:', err);
        if (err.error && err.error.message) {
          alert('Login failed: ' + err.error.message);
        } else if (err.status === 401) {
          alert('Login failed: Invalid credentials');
        } else {
          alert('Login failed: An unexpected error occurred');
        }
      },
    });
  }
}
