import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../_services/admin/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrl: './board-admin.component.css'
})
export class BoardAdminComponent implements OnInit {
 users: any[] = []

 constructor(private adminService: AdminService, private router: Router) {}
 
 ngOnInit(): void {
  this.adminService.getAllUsersInfo().subscribe((data) => {
    this.users = data;
  });
}

showUserInfo(index: number) {
  this.router.navigate(['/info'], { state: { userData: this.users[index] } })
  }
}

