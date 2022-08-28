import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Auth } from '../models/auth.model';
import { LoginForm } from '../models/forms/login.form';
import { RegisterForm } from '../models/forms/register.form';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private BASE_URL: string = "http://localhost:8989/auth";

  constructor(private _client: HttpClient) { }

  public login(form: LoginForm): Observable<Auth> {
    return this._client.post<Auth>(this.BASE_URL + "/login", form).pipe(
      tap( auth => localStorage.setItem("auth", JSON.stringify(auth)) )
    );
  }

  public get isConnected(): boolean{
    return localStorage.getItem("auth") != undefined;
  }
  
  disconnect() {
    localStorage.removeItem("auth");
  }

  register(form: RegisterForm): Observable<Auth>{
    return this._client.post<Auth>(this.BASE_URL + '/register', form );
  }

  getToken(){
    const jsonAuth = localStorage.getItem("auth");
    console.log(jsonAuth)
    return  JSON.parse( jsonAuth == undefined ? '{}' : jsonAuth )?.token
  }

}
