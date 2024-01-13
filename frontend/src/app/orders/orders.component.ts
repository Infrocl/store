import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit {
  orders: any[] = [];
  dataLoaded: boolean = false;

  constructor() { }

  ngOnInit(): void {
    const data = history.state.orderData;
    if (data) {
      this.orders = data;
      this.dataLoaded = true;
    }
  }
}
