import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FlightService {
  private _headers!: HttpHeaders;

  constructor(private http: HttpClient) {
    this._headers = new HttpHeaders({
      'Content-Type': 'application/vnd.amadeus+json',
      Authorization: `Bearer ${environment.accessToken}`,
    });
  }

  searchCityAndAirport<T>(
    location: string,
    activateHeaders: boolean
  ): Observable<T> {
    return this.http.get<T>(
      `${environment.apiUrl}/v1/reference-data/locations?subType=AIRPORT,CITY&keyword=${location}`,
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
      `${environment.apiUrl}/v2/shopping/flight-offers?originLocationCode=${originCode}&destinationLocationCode=${destinationCode}&departureDate=${dateOfDeparture}&adults=1&max=10`,
      activateHeaders ? { headers: this._headers } : {}
    );
  }

  // TODO: Cambiar endpoint
  confirmFlight<T>(data: string, activateHeaders: boolean): Observable<T> {
    return this.http.post<T>(
      `${environment.apiUrl}/flight-confirmation`,
      data,
      activateHeaders ? { headers: this._headers } : {}
    );
  }

  // TODO: Cambiar endpoint
  bookFlight<T>(data: string, activateHeaders: boolean): Observable<T> {
    return this.http.post<T>(
      `${environment.apiUrl}/flight-booking`,
      data,
      activateHeaders ? { headers: this._headers } : {}
    );
  }
}
