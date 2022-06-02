import { Component } from '@angular/core';
import { OauthService } from '../../services/oauth.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss'],
})
export class SidenavComponent {
  constructor(private oauthSvc: OauthService) {}

  onGoogleLogin() {
    this.oauthSvc.signInWithGoogle().then(data => {
      console.log(data);
    });
  }
}
