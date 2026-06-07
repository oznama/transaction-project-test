import { Component, inject, signal } from '@angular/core';
import { Header } from '../header/header';
import { TransactionService } from '../../core/services/transaction.service';
import { TransactionRequest, TransactionsResponse, TransactionUpdateStatusRequest } from '../../core/models/transaction.model';
import { form, FormField, required } from '@angular/forms/signals';
import { ErrorDetail } from '../../core/models/generic.model';
import { AlertService } from '../../core/services/alert.service';
import { EncryptionService } from '../../core/services/encryption.service';

@Component({
  selector: 'app-transaction',
  imports: [Header, FormField],
  templateUrl: './transaction.html',
  styleUrl: './transaction.scss',
})
export class Transaction {
  private transactionService = inject(TransactionService);
  private alertService = inject(AlertService);

  constructor(private encryptionService: EncryptionService) {}

  transactions = signal<TransactionsResponse[]>([]);
  isLoading = signal<boolean>(false);
  isEditing = signal<boolean>(false);
  isFirstPage = signal<boolean>(true);
  isLastPage = signal<boolean>(true);

  page = signal<number>(0);
  size = signal<number>(5);
  sort = signal<string>('id');

  pagPrev(): void {
    if(this.page() > 0) {
      this.page.set(this.page() - 1);
      this.loadTransactions();
    }
  }

  pagNext(): void {
    this.page.set(this.page() + 1);
    this.loadTransactions();
  }

  setSize(event: FocusEvent): void {
    const element = event.target as HTMLInputElement;
    const {value} = element;
    const size = Number(value);
    if ( size > 0 && size !== this.size() ) {
      this.size.set(size);
      this.page.set(0);
      this.loadTransactions();
    }
  }
  
  setSort(sort: string): void {
    if ( sort !== this.sort() ) {
      this.sort.set(sort);
    this.loadTransactions();
    }
  }

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.isLoading.set(true);
    this.transactionService.getAll(this.page(), this.size(), this.sort()).subscribe({
      next: (data) => {
        this.isFirstPage.set(data.isFirstPage);
        this.isLastPage.set(data.isLastPage);
        this.transactions.set(data.transactions);
        this.isLoading.set(false);
      },
      error: err => {
        this.isLoading.set(false);
      }
    })
  }

  cancel(tranx: TransactionsResponse): void {
    const transactionUpdateRequest: TransactionUpdateStatusRequest = {
      id: tranx.id,
      referencia: tranx.referencia,
      estatus: 'cancelar'
    };
    this.transactionService.upadteState(transactionUpdateRequest).subscribe({
      next: (data) => {
        this.loadTransactions();
      },
      error: err => {
      }
    })
  }

  private initialForm: TransactionRequest = { operacion: '', importe: '', cliente: '', secreto: '' };
  tranxModel = signal<TransactionRequest>({ ...this.initialForm })

  tranxForm = form(this.tranxModel, (schema) => {
    required(schema.operacion);
    required(schema.importe);
    required(schema.cliente);
    required(schema.secreto);
  });

  errorResponse = signal<ErrorDetail[]>([]);

  saveTransaction(event: Event): void {
    event.preventDefault();
    this.isLoading.set(true);
    this.errorResponse.set([]);
    
    const formData = this.tranxModel();

    const secretCipher = this.encryptionService.encrypt(formData.secreto);
    console.debug('SecretCipher', secretCipher);
    
    this.transactionService.create({ ...formData, secreto: secretCipher }).subscribe({
      next: (data) => {
        this.alertService.showAlert(`¡Operacion registrada correctamente con id ${data.id}!`, 'success');
        this.loadTransactions();
        this.isLoading.set(false);
        this.tranxModel.set({ ...this.initialForm });
      },
      error: (err) => {
        this.isLoading.set(false);
        this.errorResponse.set(err.error.errors);
      }
    })
  }

  getStatusStyle(status: string): string {
    return status === 'Aprobada' ? 'status-aprobed' : 'status-canceled';
  }

  getButtonDisabled(isDisabled: boolean): string {
    return !isDisabled ? 'button' : 'button-blocked';
  }
}
