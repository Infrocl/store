import { Component, OnInit } from '@angular/core';
import { StorageService } from './_services/storage/storage.service';
import { Router } from '@angular/router';
import { UserService } from './_services/user/user.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private role: string = "";
  isLoggedIn = false;
  showAdminBoard = false;
  id?: string;

  constructor(private storageService: StorageService, private userService: UserService,  private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      this.role = this.storageService.getRole();
      this.showAdminBoard = this.role.includes('ADMIN');
      this.id = this.storageService.getId();
    }
  }

  logout(): void {
        this.storageService.clean();
        window.location.reload();
  }

  goToProfilePage() {
    this.userService.getProfileInfo(this.id).subscribe((data) => {
      this.router.navigate(['/profile'], { state: { profileData: data } });
    });
  }

  goToOrdersPage() {
    this.userService.getOrdersInfo(this.id).subscribe((data) => {
      this.router.navigate(['/orders'], { state: { orderData: data } });
    });
  }

  goToPackagesPage() {
    this.userService.getPackagesInfo(this.id).subscribe((data) => {
      this.router.navigate(['/packages'], { state: { packageData: data } });
    });
  }
}
