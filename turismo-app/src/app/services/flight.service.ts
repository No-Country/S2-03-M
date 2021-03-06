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
  // https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=SYD&destinationLocationCode=BKK&departureDate=2022-11-01&returnDate=2022-11-10&adults=2&travelClass=ECONOMY&nonStop=false&max=10
  findFlight<T>(
    originCode: string,
    destinationCode: string,
    dateOfDeparture: string,
    dateOfReturn: string,
    passengers: number,
    travelClass: string,
    activateHeaders: boolean
  ): Observable<T> {
    if (dateOfReturn === '') {
      return this.http.get<T>(
        `${environment.apiUrl}/v2/shopping/flight-offers?originLocationCode=${originCode}&destinationLocationCode=${destinationCode}&departureDate=${dateOfDeparture}&adults=${passengers}&travelClass=${travelClass}&max=10`,
        activateHeaders ? { headers: this._headers } : {}
      );
    } else {
      return this.http.get<T>(
        `${environment.apiUrl}/v2/shopping/flight-offers?originLocationCode=${originCode}&destinationLocationCode=${destinationCode}&departureDate=${dateOfDeparture}&returnDate=${dateOfReturn}&adults=${passengers}&travelClass=${travelClass}&max=10`,
        activateHeaders ? { headers: this._headers } : {}
      );
    }
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
