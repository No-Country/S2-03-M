import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { map, mergeMap, of, tap, zip, BehaviorSubject } from 'rxjs';
import { Flight } from 'src/app/interfaces/flight.interface';
import * as _moment from 'moment';
import { FlightQuery } from '../../interfaces/flight-query.interface';
import { Router } from '@angular/router';
import { OauthService } from '../../services/oauth.service';
import { TokenDto } from 'src/app/models/token-dto';
import { TokenService } from '../../services/token.service';

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
  socialUser: any;
  constructor(
    private flightSvc: FlightService,
    private router: Router,
    private oauthSvc: OauthService,
    private tokenSvc: TokenService
  ) {}

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
          // console.log(res);
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
          // console.log('FlightQuery', res[0]);
          // console.log('FlightOffers', res[1].data);

          this.flightQuery = res[0];
          this.flightOffers = res[1].data;
        })
      )
      .subscribe();
  }

  onBooking(flight: Flight) {
    console.log(flight);

    this.oauthSvc.signInWithGoogle().then(data => {
      console.log(data);
      this.socialUser = data;
      const tokenGoogle = new TokenDto(this.socialUser.credential.idToken);
      const email = this.socialUser.additionalUserInfo.profile.email;

      this.oauthSvc.google(tokenGoogle, email).subscribe({
        next: (res: any) => {
          console.log(res);
          this.tokenSvc.setToken(res.value);
          this.router.navigate(['/confirmacion-vuelo']);
        },
        error: (err: any) => {
          console.log(err);
          this.logout();
        },
      });
    });
  }

  logout() {
    this.tokenSvc.logout();
    this.oauthSvc.logout();
  }
}
