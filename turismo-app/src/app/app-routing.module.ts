import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { FlightOffersComponent } from './pages/flight-offers/flight-offers.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'flight-offers', component: FlightOffersComponent },
  { path: '**', pathMatch: 'full', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
