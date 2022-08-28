import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PanierService } from 'src/app/services/panier.service';


@Component({
  selector: 'app-confirm-order',
  templateUrl: './confirm-order.component.html',
  styleUrls: ['./confirm-order.component.scss']
})
export class ConfirmOrderComponent implements OnInit {

  form: FormGroup;

  constructor(
    private _panierService: PanierService,
    private _router: Router,
    builder: FormBuilder
  ) { 
    this.form = builder.group({
      'date': []
    })
  }

  ngOnInit(): void {
  }

  confirm(){
    
    this._panierService.sendOrder(new Date(this.form.value.date)).subscribe({
      next: () => this._router.navigateByUrl("client/menu"),
      error: () => alert('une erreur s\'est produite')
    });
  }

}
