import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../_services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  form: any = {
    name: null,
    email: null,
    birthDate: null,
    password: null
  };

  isRegisted: boolean = false;
  isRegistrationFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }

  onSubmit(): void {
    const { email, name, birthDate, password } = this.form;

    this.authService.register(email, name, birthDate, password).subscribe({
      next: data => {
        this.isRegisted = true;
      },
      error: err => {

      }
    });

  }

  reloadPage(): void {
    window.location.reload();
  }
}
