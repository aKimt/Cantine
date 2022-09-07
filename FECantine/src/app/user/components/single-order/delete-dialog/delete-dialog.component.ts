import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Component, Inject } from '@angular/core';
import { OrderService } from "src/app/services/order.service";
import { AuthService } from "src/app/services/auth.service";
import { Router } from "@angular/router";
import { Commande } from "src/app/models/commande.model";
import { Subject } from "rxjs";

const $delete = new Subject<null>();
export const $deleteObs = $delete.asObservable();

@Component({
    selector: 'delete-dialog',
    templateUrl: 'delete-dialog.component.html',
    styleUrls: ['delete-dialog.component.scss']
  })
  export class DeleteDialogComponent {
    
    error?: string;
    
    constructor(
      public dialogRef: MatDialogRef<DeleteDialogComponent>, 
      private _service: OrderService,
      private _authService: AuthService,
      private _router: Router,
      @Inject(MAT_DIALOG_DATA) public data: Commande,
    ) {}
  
    onCancel(){
      this.dialogRef.close();
    }
  
    onDelete(){
      if(this._authService.isConnected){
        this._service.deleteById(this.data.id).subscribe({
            next: () => $delete.next(null),
            error: (error) => alert('une erreur s\'est produite: ' + error.message) 
        });
        this.dialogRef.close();
      }
      else{
        this.error = "vous n'êtes pas connecté";
        setTimeout( () => this._router.navigateByUrl("/login"), 2000 ) 
      }
    }
  }
  