import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from "@angular/core";
import { FormsModule, NgModel, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from "./app.component";
import { AdminLayoutComponent } from "./layouts/admin-layout/admin-layout.component";
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';

import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import {MatSortModule} from '@angular/material/sort';
import {MatSort, Sort} from '@angular/material/sort';

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
import { BasicAuthInterceptorService } from "./shared/user/basic-auth-interceptor.service";
import { MatFormField, MatFormFieldModule, MatLabel } from "@angular/material/form-field";
import { MatTable, MatTableModule } from "@angular/material/table";
import { MatPaginator, MatPaginatorIntl, MatPaginatorModule } from "@angular/material/paginator";
import { MatButtonModule } from "@angular/material/button";
import {MatInputModule} from '@angular/material/input';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    MatSortModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    ComponentsModule,
    NgbModule,
    RouterModule,
    AppRoutingModule,
    ReactiveFormsModule,
    
    ToastrModule.forRoot()
  ],
  declarations: [AppComponent,
    AdminLayoutComponent,
    AgentLayoutComponent,
    ClientLayoutComponent,
    ExpertLayoutComponent,
    AuthLayoutComponent,
    AuthenticationComponent
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS, useClass:BasicAuthInterceptorService, multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
