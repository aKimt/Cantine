import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  hide: boolean = true;
  form: FormGroup;


  constructor(
    builder: FormBuilder, 
    private _authService: AuthService,
    private _router: Router  
  ) {
    this.form = builder.group({
      login: ['', [ Validators.required, Validators.minLength(2)]],
      pwd: ['', [ Validators.required, Validators.minLength(6)]],
      confirm: ['', [ Validators.required, Validators.minLength(6)]],
      bureau: ['', Validators.required],
      prenom: ['', Validators.required],
      nom: ['', Validators.required]
    })
  }

  ngOnInit(): void {
  }

  onSubmit(){
    if(this.form.valid && this.form.value['pwd'] == this.form.value['confirm'])
      this._authService.register( this.form.value ).subscribe({
        next: () => this._router.navigateByUrl("/login")
      })
  }

}
