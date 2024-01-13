import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrl: './user-info.component.css'
})
export class UserInfoComponent {
  currentUserId: string = "";
  userName: string = "";
  email: string = "";
  registrationDate: string = "";
  birthDate: string = "";
  orders: any[] = [];
  packages: any[] = [];
  dataLoaded: boolean = false;

  constructor() { }

  ngOnInit(): void {
    const data = history.state.userData;
    if (data) {
      this.currentUserId = data.id;
      this.userName = data.name;
      this.email = data.email;
      this.registrationDate = data.registrationDate;
      this.birthDate = data.birthDate;
      this.orders = data.orders;
      this.packages = data.packages;
      this.dataLoaded = true;
    }
  }
}
