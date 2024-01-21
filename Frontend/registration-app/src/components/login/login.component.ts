import { Component } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {CommonModule} from '@angular/common';
import { LoginService } from '../../app/services/login.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatFormFieldModule,FormsModule,MatInputModule,MatButtonModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  credentials={
    userName:"",
    password:""
  }
  constructor(private loginService:LoginService){}

  onSubmit(){
    if(this.credentials.userName!='' && this.credentials.userName!=null && this.credentials.password!='' && this.credentials.password!=null){
      this.loginService.generateToken(this.credentials).subscribe(
        (response:any)=>{
          console.log(response.token);
          this.loginService.loginUser(response.token,this.credentials.userName);
          window.location.href="/dashboard"
        },
        error=>{
          console.log(error);
          alert("Login unsuccessful/Invalid credentials");
        }
      )
    }
    else{
      alert("Fields are empty");
    }
  }

}
