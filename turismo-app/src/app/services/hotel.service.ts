import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HotelService {
  private _headers!: HttpHeaders;

  constructor(private http: HttpClient) {
    this._headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${environment.accessToken}`,
    });
  }

  listHotelsByCity<T>(
    location: string,
    activateHeaders: boolean
  ): Observable<T> {
    return this.http.get<T>(
      `${environment.apiUrl}/v1/reference-data/locations/hotels/by-city?cityCode=${location}&radius=5&radiusUnit=KM`,
      activateHeaders ? { headers: this._headers } : {}
    );
  }
}
