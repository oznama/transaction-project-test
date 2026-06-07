import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ToastNotification } from './features/toast-notification/toast-notification';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ToastNotification],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('transaction-app');
}
