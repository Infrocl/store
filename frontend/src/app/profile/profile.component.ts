import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUserId: any;
  userName: string = "";
  email: string = "";
  registrationDate: string = "";
  birthDate: string = "";
  dataLoaded: boolean = false;

  constructor() { }

  ngOnInit(): void {
    const data = history.state.profileData;
    if (data) {
      this.userName = data.name;
      this.email = data.email;
      this.registrationDate = data.registrationDate;
      this.birthDate = data.birthDate;
      this.dataLoaded = true;
    }
  }
}
