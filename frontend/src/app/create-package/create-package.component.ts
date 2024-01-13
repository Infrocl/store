import { Component } from '@angular/core';
import { AdminService } from '../_services/admin/admin.service';

@Component({
  selector: 'app-create-package',
  templateUrl: './create-package.component.html',
  styleUrl: './create-package.component.css'
})
export class CreatePackageComponent {
  form: any = {
    userId: null,
    orders: [""],
    shipmentDate: null,
    deliveryDate: null
  };
  isCreated = false;

  constructor(private adminService: AdminService) {}

  createPackage() {
    const {userId, orders, shipmentDate, deliveryDate } = this.form;

    this.adminService.createPackage(userId, orders, shipmentDate, deliveryDate).subscribe({
      next: data => {
       this.isCreated = true;
      }
    });
  }

  addOrderField() {
    this.form.orders.push(''); 
  }
}
