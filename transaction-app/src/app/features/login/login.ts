import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { form, FormField, minLength, required } from '@angular/forms/signals';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormField],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private authService = inject(AuthService);
  private router = inject(Router);

  errorMessage = signal<string | null>(null);
  isLoading = signal<boolean>(false);

  loginModel = signal({ usuario: '', password: '' })

  loginForm = form(this.loginModel, (schema) => {
    required(schema.usuario);
    required(schema.password);
    minLength(schema.password, 6);
  });

  onSubmit(event: Event): void {
    event.preventDefault();
    this.isLoading.set(true);
    this.errorMessage.set(null);
    
    const formData = this.loginModel();
    this.authService.login(formData).subscribe({
      next: () => {
        this.router.navigate(['/transactions']);
      },
      error: (err) => {
        this.errorMessage.set('Credenciales invalidas');
        this.isLoading.set(false);
      }
    })

  }
}
