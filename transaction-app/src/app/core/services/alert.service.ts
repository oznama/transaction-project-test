import { Injectable, signal } from "@angular/core";
import { Alert, AlertType } from "../models/alert.model";

@Injectable({
    providedIn: 'root'
})
export class AlertService {
    alerts = signal<Alert[]>([]);
    private nextId = 0;

    showAlert(message: string, type: AlertType = 'info') {
        const id = this.nextId++;
        const newAlert: Alert = { id, message, type };
        this.alerts.update(current => [...current, newAlert]);
        setTimeout(() => this.dismissAlert(id), 5000);
    }

    dismissAlert(id: number) {
        this.alerts.update(current => current.filter(a => a.id !== id));
    }
}