import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PecasListComponent } from './compenents/pecas-list/pecas-list.component';
import { PecaAddComponent } from './compenents/peca-add/peca-add.component';
import { FormsModule } from '@angular/forms';
import { PecaEditComponent } from './compenents/peca-edit/peca-edit.component';
import { LoginComponent } from './compenents/login/login.component';
import { HomeComponent } from './compenents/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    PecasListComponent,
    PecaAddComponent,
    PecaEditComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
