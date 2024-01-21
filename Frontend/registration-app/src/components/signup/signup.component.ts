import { Component } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {CommonModule} from '@angular/common';
import { LoginService } from '../../app/services/login.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [MatFormFieldModule,FormsModule,MatInputModule,MatButtonModule,CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  credentials={
    userName:"",
    email:"",
    firstName:"",
    lastName:"",
    password:""
  }

  constructor(private loginService:LoginService){}

  onSubmit(){
    if(!this.isNullUndefinedEmpty(this.credentials.userName) && !this.isNullUndefinedEmpty(this.credentials.email) && !this.isNullUndefinedEmpty(this.credentials.firstName) && !this.isNullUndefinedEmpty(this.credentials.lastName) && !this.isNullUndefinedEmpty(this.credentials.password)){
      this.loginService.registerUser(this.credentials).subscribe(
        (response:any)=>{
          alert("Signup successful");
          window.location.href="/"
        },
        error=>{
          console.log(error);
          alert("Signup unsuccessful");
        }
      )
    }
    else{
      alert("Fields are empty");
    }
  }

  isNullUndefinedEmpty(a:any){
    if(a==null||a==undefined||a=='') return true;
    else return false;
  }
}
