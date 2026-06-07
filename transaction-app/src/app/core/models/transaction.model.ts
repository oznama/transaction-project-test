export interface TransactionList {
    isFirstPage: boolean,
    isLastPage: boolean,
    transactions: TransactionsResponse[]
}

export interface TransactionsResponse {
    id: number,
    operacion: string,
    importe: number,
    cliente: string,
    referencia: number,
    estatus: string
}

export interface TransactionRequest {
    operacion: string,
    importe: string,
    cliente: string,
    secreto: string
}

export interface TransactionResponse {
    id: number,
    estatus: string,
    referencia: string,
    operacion: string
}

export interface TransactionUpdateStatusRequest {
    id: number,
    referencia: number,
    estatus: string
}