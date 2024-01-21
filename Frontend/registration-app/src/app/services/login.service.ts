import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})

export class LoginService {

  url="http://localhost:8080"
  constructor(private http:HttpClient) { }

  generateToken(credentials:any){
    return this.http.post(`${this.url}/login`,credentials)
  }

  registerUser(credentials:any){
    return this.http.post(`${this.url}/signup`,credentials)
  }

  updateData(credentials:any){
    return this.http.put(`${this.url}/edit`,credentials)
  }

  loginUser(token: string, userName: string){
    localStorage.setItem("token",token);
    localStorage.setItem("userName",userName);
    return true;
  }

  isLoggedIn(){
    let token = localStorage.getItem("token");
    if(token==null||token==undefined||token=='') return false;
    else return true;
  }

  logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("userName");
    return true;
  }

  getToken(){
    return localStorage.getItem("token");
  }
}
