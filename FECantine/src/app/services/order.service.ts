import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Commande } from '../models/commande.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {



  constructor(private _client: HttpClient) { }

  public getMyOrders(): Observable<Commande[]>{
    return this._client.get<Commande[]>("http://localhost:8080/commande/my-orders")
  }

  public deleteById(id: string): Observable<unknown>{
    return this._client.delete("http://localhost:8080/commande/delete", {params: {id: id}})
  }
}
