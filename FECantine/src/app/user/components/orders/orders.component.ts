import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Commande } from 'src/app/models/commande.model';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  orders?: Commande[]

  constructor(private _service: OrderService, private _changeDetector: ChangeDetectorRef ) {
    this._service.getMyOrders().subscribe( (resp) => {
      this.orders = [...resp]
    });
  }

  ngOnInit(): void {
  }

  loadOrders(){
    this._service.getMyOrders().subscribe( (resp) => {
      this.orders = [...resp];
      console.log()
    });
  }


}
