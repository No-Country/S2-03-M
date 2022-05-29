import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { map, mergeMap, of, tap, zip } from 'rxjs';
import { Flight } from 'src/app/interfaces/flight.interface';

@Component({
  selector: 'app-flight-offers',
  templateUrl: './flight-offers.component.html',
  styleUrls: ['./flight-offers.component.scss'],
})
export class FlightOffersComponent implements OnInit {
  flightQuery: any;
  flightOffers: any;
  constructor(private flightSvc: FlightService) {}

  ngOnInit(): void {
    this.flightSvc.flightQuery$
      .pipe(
        tap((res: any) => {
          console.log(res);
        }),
        mergeMap((res: any) =>
          zip(
            of(res),
            this.flightSvc.findFlight<Flight[]>(
              res.from,
              res.to,
              res.departure,
              true
            )
          )
        ),
        map((res: any) => {
          console.log(res);
          this.flightQuery = res[0];
          this.flightOffers = res[1].data;
        })
      )
      .subscribe();
  }
}
