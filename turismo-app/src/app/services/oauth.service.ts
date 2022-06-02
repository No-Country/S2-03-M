import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import firebase from 'firebase/compat/app';

@Injectable({
  providedIn: 'root',
})
export class OauthService {
  constructor(private afAuth: AngularFireAuth) {}

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
}
