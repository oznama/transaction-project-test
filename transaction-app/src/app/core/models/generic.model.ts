export interface GenericResponse {
    code: number,
    message: string,
    entity: any
}

export interface ErrorDetail {
    property: string,
    message: string
}