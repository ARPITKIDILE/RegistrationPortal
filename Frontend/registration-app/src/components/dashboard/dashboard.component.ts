import { Component } from '@angular/core';
import { LoginService } from '../../app/services/login.service';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [MatButtonModule,MatInputModule,FormsModule,CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  constructor(private loginService:LoginService){}

  credentials={
    email:"",
    userName: localStorage.getItem("userName")
  }

  logout(){
    this.loginService.logout();
    window.location.href="/"
  }

  onSubmit(){
    if(this.credentials.email!='' && this.credentials.email!=null){
      this.loginService.updateData(this.credentials).subscribe(
        (response:any)=>{
          alert("Data Updated successfully");
        },
        error=>{
          console.log(error);
          alert("Data Updation unsuccessful");
        }
      )
    }
    else{
      alert("Fields are empty");
    }
  }
}
