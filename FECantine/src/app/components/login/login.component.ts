import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  hide: boolean = true;
  firstTry: boolean = true;

  constructor(
    builder: FormBuilder, 
    private _service: AuthService,
    private _router: Router
  ) {
    this.form = builder.group({
      username: ['',Validators.required],
      password: ['',Validators.required]
    })
  }

  ngOnInit(): void {
  }

  onSubmit(){
    if(this.form.valid)
      this._service.login(this.form.value).subscribe( 
        {
          next: () => this._router.navigateByUrl("/"),
          error: () => this.firstTry = false
        }
      );
  }

}
