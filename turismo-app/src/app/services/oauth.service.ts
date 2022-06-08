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

  public google(tokenDto: TokenDto): Observable<TokenDto> {
    return this.http.post<TokenDto>(
      `${this.oauthURL}/oauth/google`,
      tokenDto,
      headers
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
