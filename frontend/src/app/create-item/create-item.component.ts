import { Component, OnInit } from '@angular/core';
import { AdminService } from '../_services/admin/admin.service';
import { Location } from "@angular/common";

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrl: './create-item.component.css'
})
export class CreateItemComponent implements OnInit {
  form: any = {
    name: null,
    price: null
  };
  isCreated = false;
  isUpdated = false;
  toCreate = this.location.path().includes("new");
  itemId: any;

  constructor(private adminService: AdminService, private location: Location) {}

  ngOnInit(): void {
    const data = history.state.itemData;
    if (data) {
      this.itemId = data.id;
      this.form.name = data.name;
      this.form.price = data.price;
    }
  }

  createItem() {
    const { name, price } = this.form;

    this.adminService.createItem(name, price).subscribe({
      next: data => {
       this.isCreated = true;
      }
    });
  }

  updateItem() {
    const { name, price } = this.form;
    this.adminService.updateItem(this.itemId, name, price).subscribe({
      next: data => {
        this.isUpdated = true;
      }
    });
  }
}
