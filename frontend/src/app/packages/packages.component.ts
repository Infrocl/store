import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-packages',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './packages.component.html',
  styleUrl: './packages.component.css'
})
export class PackagesComponent implements OnInit {
  packages: any[] = [];
  dataLoaded: boolean = false;

  constructor() { }

  ngOnInit(): void {
    const data = history.state.packageData;
    if (data) {
      this.packages = data;
      this.dataLoaded = true;
    }
  }
}







