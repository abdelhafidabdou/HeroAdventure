import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { FormsModule } from '@angular/forms';
import { MoveHeroComponent } from './move-hero/move-hero.component';
import {HttpClientModule} from "@angular/common/http";
import {HeroService} from "./services/hero-service.service";

@NgModule({
  declarations: [
    AppComponent,

    MoveHeroComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [HeroService],
  bootstrap: [AppComponent]
})
export class AppModule { }
