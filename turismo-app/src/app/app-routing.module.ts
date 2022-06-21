import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { FlightOffersComponent } from './pages/flight-offers/flight-offers.component';
import { HotelsComponent } from './pages/hotels/hotels.component';
import { Hotels1Component } from './pages/hotels1/hotels1.component';
import { FlightConfirmationComponent } from './pages/flight-confirmation/flight-confirmation.component';

const routes: Routes = [
  { path: 'vuelos', component: HomeComponent },
  { path: 'vuelos-disponibles', component: FlightOffersComponent },
  { path: 'confirmacion-vuelo', component: FlightConfirmationComponent },
  { path: 'hoteles', component: HotelsComponent },
  { path: 'hoteles/:iataCode', component: Hotels1Component },
  { path: '**', pathMatch: 'full', redirectTo: 'vuelos' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
