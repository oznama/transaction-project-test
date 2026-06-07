export interface AuthRequest {
    usuario: string,
    password: string
}

export interface AuthResponse {
    token: string
}