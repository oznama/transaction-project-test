import CryptoJS from "crypto-js";
import { environment } from "../../../environments/environment";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class EncryptionService {
    private readonly secretKey = environment.secretKey;
    private readonly ivString = environment.ivString;

    private readonly key = CryptoJS.enc.Utf8.parse(this.secretKey);
    private readonly iv = CryptoJS.enc.Utf8.parse(this.ivString);

    encrypt(value: string): string {
        const encrypted = CryptoJS.AES.encrypt(value, this.key, {
            iv: this.iv,
            mode: CryptoJS.mode.CBC,
            padding: CryptoJS.pad.Pkcs7
        });
        return encrypted.toString();
    }
}