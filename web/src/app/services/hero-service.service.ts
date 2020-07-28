import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {MoveDetails} from "../move-hero/MoveDetails";
import { Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';


@Injectable()
export class HeroService {

  url: string = "http://localhost:8080/api";
  moveUrl: string = this.url+"/card/move";
  getCardUrl: string = this.url+"/card";

  constructor(private httpClient:HttpClient) { }



  getHistory(idAccount,idCustomer){

     const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
        'mon-entete-personnalise':'maValeur'
      })
    };

    return this.httpClient
      .get(this.url+"/accounts/transactions-history?idCustomer="+idCustomer+"&idAcount="+idAccount,optionRequete)

  }

  depositMoney(idAccount,amount){
    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
        'mon-entete-personnalise':'maValeur'
      })
    };
    let date: Date = new Date();
    return this.httpClient.post(this.url+"/accounts/deposit?amount="+amount+"&id="+idAccount,optionRequete)

  }

  withdrawMoney(idAccount,amount){
    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*'
      })
    };
    let date: Date = new Date();
    return this.httpClient.post(this.url+"/accounts/withdraw?amount="+amount+"&id="+idAccount,optionRequete)

  }

  getBalance(idAccount){

    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*'
      })
    };
    let date: Date = new Date();
    return this.httpClient.get(this.url+"/accounts/balance/"+idAccount,optionRequete)

  }
  getHistoryList(idAccount){
    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
        'mon-entete-personnalise':'maValeur'
      })
    };

    return this.httpClient
      .get(this.url+"/accounts/"+idAccount+"/history",optionRequete)
  }



  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
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
