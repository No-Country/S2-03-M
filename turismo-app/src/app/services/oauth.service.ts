import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import firebase from 'firebase/compat/app';
import { Router } from '@angular/router';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { TokenDto } from '../models/token-dto';
import { Observable } from 'rxjs';

const headers = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class OauthService {
  oauthURL = 'http://localhost:8080';

  constructor(
    private afAuth: AngularFireAuth,
    private router: Router,
    private http: HttpClient
  ) {}

  public google(tokenDto: TokenDto, email: string): Observable<TokenDto> {
    const user = [tokenDto, email];
    const userStr = JSON.stringify(user);
    console.log(userStr);
    const tokenDtoStr = JSON.stringify(tokenDto);

    return this.http.post<TokenDto>(
      `${this.oauthURL}/oauth/google`,
      {},
      {
        headers: new HttpHeaders({
          Authorization: tokenDtoStr,
          'Content-Type': 'application/json',
          email: email,
        }),
      }
    );
  }

  async signInWithGoogle() {
    try {
      return await this.afAuth.signInWithPopup(
        new firebase.auth.GoogleAuthProvider()
      );
    } catch (err) {
      console.log(err);
      return null;
    }
  }

  async logout() {
    await this.afAuth.signOut();
    localStorage.clear();
    this.router.navigate(['/vuelos']);
  }
}
