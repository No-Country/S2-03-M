import { Component } from '@angular/core';
import { OauthService } from '../../services/oauth.service';
import { AngularFireAuth } from '@angular/fire/compat/auth';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss'],
})
export class SidenavComponent {
  constructor(private oauthSvc: OauthService, public auth: AngularFireAuth) {}

  onGoogleLogin() {
    this.oauthSvc.signInWithGoogle().then(data => {
      console.log(data);
    });
  }

  logout() {
    this.oauthSvc.logout();
  }
}
