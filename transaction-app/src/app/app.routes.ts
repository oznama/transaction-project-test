import { Routes } from '@angular/router';
import { Login } from './features/login/login';
import { Transaction } from './features/transaction/transaction';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
    {
        path: 'login',
        component: Login
    },
    {
        path: 'transactions',
        component: Transaction,
        canActivate: [authGuard]
    },
    {
        path: '**',
        redirectTo: 'login',
        pathMatch: 'full'
    }
];
