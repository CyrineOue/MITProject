import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from "@angular/core";
import { FormsModule, NgModel } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from "./app.component";
import { AdminLayoutComponent } from "./layouts/admin-layout/admin-layout.component";
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';

import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { AppRoutingModule } from "./app-routing.module";
import { ComponentsModule } from "./components/components.module";
import { AuthenticationComponent } from './authentication/authentication.component';
import { ClientLayoutComponent } from './layouts/client-layout/client-layout.component';
import { ContractsComponent } from './pages/contracts/contracts.component';
import { PaymentsComponent } from './pages/payments/payments.component';
import { SinistersComponent } from './pages/sinisters/sinisters.component';
import { ExpertLayoutComponent } from "./layouts/expert-layout/expert-layout.component";
import { AgentLayoutComponent } from "./layouts/agent-layout/agent-layout.component";
import { ClienttableComponent } from './pages/clienttable/clienttable.component';
import { ProductsComponent } from './pages/products/products.component';
import { AgenttableComponent } from './pages/agenttable/agenttable.component';
import { ExperttableComponent } from './pages/experttable/experttable.component';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ComponentsModule,
    NgbModule,
    RouterModule,
    AppRoutingModule,
    ToastrModule.forRoot()
  ],
  declarations: [AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent,
    AuthenticationComponent,
    ClientLayoutComponent,
    AgentLayoutComponent,
    ExpertLayoutComponent,
    ClienttableComponent,
    ProductsComponent,
    AgenttableComponent,
    ExperttableComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
