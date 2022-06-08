import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './modules/material/material.module';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { Dialog1Component } from './components/dialog1/dialog1.component';
import { HttpClientModule } from '@angular/common/http';
import { FlightOffersComponent } from './pages/flight-offers/flight-offers.component';

import { environment } from '../environments/environment';
import { AngularFireModule } from '@angular/fire/compat';
import { HotelsComponent } from './pages/hotels/hotels.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SidenavComponent,
    Dialog1Component,
    FlightOffersComponent,
    HotelsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    AngularFireModule.initializeApp(environment.firebase),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
