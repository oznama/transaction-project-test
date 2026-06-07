import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TransactionList, TransactionRequest, TransactionResponse, TransactionUpdateStatusRequest } from "../models/transaction.model";
import { GenericResponse } from "../models/generic.model";
import { environment } from "../../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class TransactionService {
    private api = environment.apiMainUrl;

    constructor(private http: HttpClient) {}

    getAll(page: number, size: number, sort: string): Observable<TransactionList> {
        return this.http.get<TransactionList>(`${this.api}?page=${page}&size=${size}&sort=${sort}`);
    }

    create(transactionRequest: TransactionRequest): Observable<TransactionResponse> {
        return this.http.post<TransactionResponse>(this.api, transactionRequest);
    }

    upadteState(transactionUpdateRequest: TransactionUpdateStatusRequest): Observable<GenericResponse> {
        return this.http.patch<GenericResponse>(this.api, transactionUpdateRequest);
    }
}