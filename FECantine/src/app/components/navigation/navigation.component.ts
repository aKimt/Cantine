import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { PanierService } from 'src/app/services/panier.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {


  constructor(private _panierServ: PanierService, private _authServ: AuthService) { }

  ngOnInit(): void {
  }

  openPanier(){
    this._panierServ.togglePanier();
  }

  get panierTotal(){
    return this._panierServ.total
  }

  get isConnected(){
    return this._authServ.isConnected
  }

  onDisconnect(){
    this._authServ.disconnect();
  }

}
