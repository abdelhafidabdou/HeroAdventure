import {Coordinate} from "./Coordinate";

export class MoveDetails {
  steps: string;
  initialCoordinates: Coordinate;
  constructor() { }
}


export class Card {

   width: number;
   length: number;
  occupiedSquares: Coordinate[];
  constructor() { }
}
