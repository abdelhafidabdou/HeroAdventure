import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {MoveDetails} from "../move-hero/move-details";



@Injectable()
export class HeroService {

  url: string = "http://localhost:8080/api";
  moveUrl: string = this.url+"/card/move";
  getCardUrl: string = this.url+"/card";

  constructor(private httpClient:HttpClient) { }





  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }

  }


  moveTo(moveDetails:MoveDetails){
    return this.httpClient.post(this.moveUrl,moveDetails) ;
  }

  getCard(){
    return this.httpClient.get(this.getCardUrl) ;
  }


}
