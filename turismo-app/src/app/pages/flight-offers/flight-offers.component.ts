import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { map, mergeMap, of, tap, zip } from 'rxjs';
import { Flight } from 'src/app/interfaces/flight.interface';
import * as _moment from 'moment';
import { FlightQuery } from '../../interfaces/flight-query.interface';

@Component({
  selector: 'app-flight-offers',
  templateUrl: './flight-offers.component.html',
  styleUrls: ['./flight-offers.component.scss'],
})
export class FlightOffersComponent implements OnInit {
  public flightQuery!: FlightQuery;
  public flightOffers!: Flight[];
  public moment = _moment;
  constructor(private flightSvc: FlightService) {}

  ngOnInit(): void {
    this.flightSvc.flightQuery$
      .pipe(
        tap((res: FlightQuery) => {
          console.log(res);
        }),
        mergeMap((res: FlightQuery) =>
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
        map((res: [FlightQuery, any]) => {
          console.log('FlightQuery', res[0]);
          console.log('FlightOffers', res[1].data);

          this.flightQuery = res[0];
          this.flightOffers = res[1].data;
        })
      )
      .subscribe();
  }
}
