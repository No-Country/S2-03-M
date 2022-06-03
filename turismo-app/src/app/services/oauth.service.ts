import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import firebase from 'firebase/compat/app';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class OauthService {
  constructor(private afAuth: AngularFireAuth, private router: Router) {}

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
