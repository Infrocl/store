import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login-component/login.component';
import { HomeComponent } from './home/home.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { ProfileComponent } from './profile/profile.component';
import { OrdersComponent } from './orders/orders.component';
import { PackagesComponent } from './packages/packages.component';
import { CartComponent } from './cart/cart.component';
import { CreateItemComponent } from './create-item/create-item.component';
import { CreatePackageComponent } from './create-package/create-package.component';
import { UserInfoComponent } from './user-info/user-info.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'packages', component: PackagesComponent },
  { path: 'cart', component: CartComponent },
  { path: 'admin/item/new', component: CreateItemComponent },
  { path: 'admin/item/update', component: CreateItemComponent },
  { path: 'admin/package/new', component: CreatePackageComponent },
  { path: 'info', component: UserInfoComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
