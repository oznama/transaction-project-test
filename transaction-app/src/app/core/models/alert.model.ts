export type AlertType = 'success' | 'danger' | 'info' | 'warning';

export interface Alert {
    id: number;
    type: AlertType;
    message: string;
}