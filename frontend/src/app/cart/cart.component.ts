import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../_services/cart/cart.service';
import { StorageService } from '../_services/storage/storage.service';
import { NotificationService } from '../_services/notification/notification.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  items: any[] = this.cartService.items;
  isPaid: boolean = false;

  constructor(private cartService: CartService, private storageService: StorageService, private notificationService: NotificationService) {}

  createOrder() {
    this.cartService.createOrder(this.storageService.getId(),this.items, this.isPaid).subscribe({
      next: data => {
        this.notificationService.showNotification('The order has been successfully created', 'Close');
        this.cartService.clearCart();
        this.items = [];
      },
      error: err => {
        this.notificationService.showNotification('Error when creating an order', 'Close');
      }
    });
  }

}
