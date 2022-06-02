import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { map, mergeMap, of, tap, zip, BehaviorSubject } from 'rxjs';
import { Flight } from 'src/app/interfaces/flight.interface';
import * as _moment from 'moment';
import { FlightQuery } from '../../interfaces/flight-query.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-flight-offers',
  templateUrl: './flight-offers.component.html',
  styleUrls: ['./flight-offers.component.scss'],
})
export class FlightOffersComponent implements OnInit {
  public flightQuery!: FlightQuery;
  public fligthQuerySource = new BehaviorSubject<FlightQuery>({
    from: '',
    to: '',
    departure: '',
    return: '',
    trip: '',
    passengers: 1,
    travelClass: '',
  });
  public flightQuery$ = this.fligthQuerySource.asObservable();
  public flightOffers!: Flight[];
  public moment = _moment;
  constructor(private flightSvc: FlightService, private router: Router) {}

  ngOnInit(): void {
    const flightQuery = localStorage.getItem('flightQuery');
    if (flightQuery !== null) {
      this.fligthQuerySource.next(JSON.parse(flightQuery));
    } else {
      this.router.navigate(['/']);
    }

    this.flightQuery$
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
              res.return,
              res.passengers,
              res.travelClass,
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
