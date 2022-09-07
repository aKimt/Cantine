import { Component, Input, OnInit, EventEmitter, Output, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { Commande } from 'src/app/models/commande.model';
import { $deleteObs, DeleteDialogComponent } from './delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-single-order',
  templateUrl: './single-order.component.html',
  styleUrls: ['./single-order.component.scss']
})
export class SingleOrderComponent implements OnInit, OnDestroy {

  @Input()
  order!: Commande;

  @Output()
  refreshNeeded = new EventEmitter<null>()

  panelOpenState= false;
  subClose!: Subscription;

  constructor(public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.subClose = $deleteObs.subscribe(() => { 
      this.refreshNeeded.emit(null);
    })
  }

  openDialog(): void {
    this.dialog.open(DeleteDialogComponent, {width: '400px', data: this.order});
  }

  ngOnDestroy(): void {
      this.subClose.unsubscribe();
  }
}


