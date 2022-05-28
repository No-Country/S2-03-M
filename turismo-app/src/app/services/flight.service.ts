import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FlightService {
  private _headers!: HttpHeaders;
  public flightQuerySource = new BehaviorSubject<any>({});
  public flightQuery$ = this.flightQuerySource.asObservable();

  constructor(private http: HttpClient) {
    this._headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
  }

  searchCityAndAirport<T>(
    location: string,
    activateHeaders: boolean
  ): Observable<T> {
    return this.http.get<T>(
      `${environment.apiUrl}/city-and-airport-search/${location}`,
      activateHeaders ? { headers: this._headers } : {}
    );
  }

  findFlight<T>(
    originCode: string,
    destinationCode: string,
    dateOfDeparture: string,
    activateHeaders: boolean
  ): Observable<T> {
    return this.http.get<T>(
      `${environment.apiUrl}/flight-search?originCode=${originCode}&destinationCode=${destinationCode}&dateOfDeparture=${dateOfDeparture}`,
      activateHeaders ? { headers: this._headers } : {}
    );
  }

  confirmFlight<T>(data: string, activateHeaders: boolean): Observable<T> {
    return this.http.post<T>(
      `${environment.apiUrl}/flight-confirmation`,
      data,
      activateHeaders ? { headers: this._headers } : {}
    );
  }

  bookFlight<T>(data: string, activateHeaders: boolean): Observable<T> {
    return this.http.post<T>(
      `${environment.apiUrl}/flight-booking`,
      data,
      activateHeaders ? { headers: this._headers } : {}
    );
  }
}
