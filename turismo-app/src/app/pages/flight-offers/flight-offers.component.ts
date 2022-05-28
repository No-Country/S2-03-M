import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { tap } from 'rxjs';

@Component({
  selector: 'app-flight-offers',
  templateUrl: './flight-offers.component.html',
  styleUrls: ['./flight-offers.component.scss'],
})
export class FlightOffersComponent implements OnInit {
  constructor(private flightSvc: FlightService) {}

  ngOnInit(): void {
    this.flightSvc.flightQuery$
      .pipe(
        tap((res: any) => {
          console.log(res);
        })
      )
      .subscribe();
  }
}
