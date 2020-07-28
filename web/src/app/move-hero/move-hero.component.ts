import { Component, OnInit } from '@angular/core';
import {HeroService} from "../services/hero-service.service";
import {Card, MoveDetails} from "./move-details";
import {Coordinate} from "./Coordinate";

@Component({
  selector: 'app-move-hero',
  templateUrl: './move-hero.component.html',
  styleUrls: ['./move-hero.component.css']
})
export class MoveHeroComponent implements OnInit {
  matrix : Array<Object>  ;
  err:any;
  x: number;
  y: number;
  showResult:boolean = false;
  moves: string = '';
  length = [0,1,2,3,4,5,6,7,8,9];
  width  = [0,1,2,3,4,5,6,7,8,9];
  coordinatesAfterMove: Coordinate = new Coordinate() ;

  constructor(private heroService:HeroService) {
    this.x = 0;
    this.y = 0;


  }

  ngOnInit() {
    this.creatMatrix();
  }

  concat(direction){
   this.moves = this.moves + direction ;
  }

  deleteLastMove(){
    this.moves = this.moves.substring(0,this.moves.length-1);
  }

  deleteAllMove(){
    this.moves = '';
  }

  moveTo(){

    var coordinate :Coordinate = {x: this.x , y: this.y};
    var moveDetails: MoveDetails = {
      steps: this.moves,
      initialCoordinates : coordinate
    };
    this.moves = '';
    this.heroService.moveTo(moveDetails).subscribe(
      data => {

         Object.assign(this.coordinatesAfterMove, data);
         this.err=null;
         this.showResult = true;
        // this.matrix[this.y][this.x] = 'PL';
        },
      err => { this.err = err; console.log(err); this.showResult = false;}
    );

  }

  creatMatrix(){
    var card:Card = new Card();
    this.heroService.getCard().subscribe(
      data => {
          Object.assign(card, data);
          this.matrix = new Array(20);
          for (var i = 0; i < 20; i++) {
            this.matrix[i] = new Array(20);
          }
          for (var i = 0; i < card.occupiedSquares.length; i++) {
            this.matrix[card.occupiedSquares[i].y][card.occupiedSquares[i].x] = '#';
            console.log(card.occupiedSquares[i]);
          }
          this.length = this.generateList(card.length);
          this.width = this.generateList(card.width);
      },
      err => { this.err = err; console.log(err);}
    );
    console.log(card);
  }

  updateInitialCoord(param){
    this.matrix[this.y][this.x] = 'PL';
  }

  generateList(x) {
    var arr = new Array(x+1);
    for (var i = 0; i <= x; i++) {
       arr[i] = i;
    }
    return arr;
  }
}
