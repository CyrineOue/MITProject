import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { FooterComponent } from "./footer/footer.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { SidebarclientComponent } from './sidebarclient/sidebarclient.component';
import { SidebaragentComponent } from './sidebaragent/sidebaragent.component';
import { SidebarexpertComponent } from './sidebarexpert/sidebarexpert.component';

@NgModule({
  imports: [CommonModule, RouterModule, NgbModule],
  declarations: [FooterComponent, NavbarComponent, SidebarComponent, SidebarclientComponent, SidebaragentComponent, SidebarexpertComponent],
  exports: [FooterComponent, NavbarComponent, SidebarComponent, SidebarclientComponent, SidebaragentComponent, SidebarexpertComponent]
})
export class ComponentsModule {}
