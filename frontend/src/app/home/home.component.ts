import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user/user.service';
import { CartService } from '../_services/cart/cart.service';
import { NotificationService } from '../_services/notification/notification.service';
import { StorageService } from '../_services/storage/storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
items: any[] = []
itemName: string = "";
isUser: boolean = this.storageService.getRole().includes('USER');
isAdmin = this.storageService.getRole().includes('ADMIN');

  constructor(private userService: UserService, private cartService: CartService, private notificationService: NotificationService,
    private storageService: StorageService, private router: Router) { }

  ngOnInit(): void {
    this.getAllItems();
  }

  getAllItems(): void {
    this.userService.getItemsInfo().subscribe((data) => {
      this.items = data;
    });
  }

  searhItem(): void {
    const itemName = this.itemName;

    this.userService.getItemsByName(itemName).subscribe({
      next: data => {
        this.items = data;
      }
    });
  }

  addToCart(index: number): void {
    this.cartService.addToCart(this.items[index]);
    this.notificationService.showNotification('Successfully added', 'Close');
  }

  update(index: number): void {
    this.router.navigate(['/admin/item/update'], { state: { itemData: this.items[index] } });
  }
}



