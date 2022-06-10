import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { FlightOffersComponent } from './pages/flight-offers/flight-offers.component';
import { HotelsComponent } from './pages/hotels/hotels.component';

const routes: Routes = [
  { path: 'vuelos', component: HomeComponent },
  { path: 'vuelos-disponibles', component: FlightOffersComponent },
  { path: 'hoteles', component: HotelsComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'vuelos' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
