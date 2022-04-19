import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { BrowserModule } from "@angular/platform-browser";
import { Routes, RouterModule } from "@angular/router";

import { AdminLayoutComponent } from "./layouts/admin-layout/admin-layout.component";
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { AuthenticationComponent } from "./authentication/authentication.component";
import { ClientLayoutComponent } from "./layouts/client-layout/client-layout.component";

const routes: Routes = [
  /*{
    path: "",
    redirectTo: "home",
    pathMatch: "full"
  },*/
  {
    path: 'log',
    component: AuthenticationComponent
  },
  {
    path: 'log/admin',
    component: AdminLayoutComponent,
    children: [
      {
        path: "",
        loadChildren: () => import ("./layouts/user-layout.module").then(m => m.AdminLayoutModule)
      }
    ]
  }, {
    path: 'log/admin',
    component: AuthLayoutComponent,
    children: [
      {
        path: "log/admin",
        loadChildren: () => import ("./layouts/auth-layout/auth-layout.module").then(m => m.AuthLayoutModule)
      }
    ]
  },
  {
    path: 'log/client',
    component: ClientLayoutComponent,
    children: [
      {
        path: "log/client",
        loadChildren: () => import ("./layouts/user-layout.module").then(m => m.ClientLayoutModule)
      }
    ]
  }, {
    path: 'log/client',
    component: AuthLayoutComponent,
    children: [
      {
        path: "log/client",
        loadChildren: () => import ("./layouts/auth-layout/auth-layout.module").then(m => m.AuthLayoutModule)
      }
    ]
  },
  {
    path: "**",
    redirectTo: "dashboard"
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes, {
      useHash: true
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
