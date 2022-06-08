import { Component } from '@angular/core';
import { OauthService } from '../../services/oauth.service';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { TokenService } from '../../services/token.service';
import { TokenDto } from '../../models/token-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss'],
})
export class SidenavComponent {
  socialUser: any;
  constructor(
    private oauthSvc: OauthService,
    public auth: AngularFireAuth,
    private tokenSvc: TokenService,
    private router: Router
  ) {}

  onGoogleLogin() {
    this.oauthSvc.signInWithGoogle().then(data => {
      console.log(data);
      this.socialUser = data;
      const tokenGoogle = new TokenDto(this.socialUser.credential.idToken);
      this.oauthSvc.google(tokenGoogle).subscribe({
        next: (res: any) => {
          console.log(res);
          this.tokenSvc.setToken(res.value);
          this.router.navigate(['/']);
        },
        error: (err: any) => {
          console.log(err);
          this.logout();
        },
      });
    });
  }

  logout() {
    this.tokenSvc.logout();
    this.oauthSvc.logout();
  }
}
