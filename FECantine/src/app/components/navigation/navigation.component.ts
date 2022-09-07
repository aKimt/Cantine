import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { PanierService } from 'src/app/services/panier.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {


  constructor(private _panierServ: PanierService, private _authServ: AuthService, private _router: Router) { }

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
    this._router.navigate(['client', 'accueil']);
  }

}
