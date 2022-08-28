export interface Auth{
    login: string;
    roles: string[];
    token: string;
    expiresAt: Date;
}