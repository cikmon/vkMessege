import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {ListAllCoubsModule} from './ListAllCoubs/listAllCoubs.module';
import {RouterModule} from '@angular/router';
import {StoreFirstGuard} from './storeFirst.guard';
import {ListAllCoubsComponent} from './ListAllCoubs/listAllCoubs.component';
import {StaticDataSource} from './service/static.data';
import { UserComponent } from './user/user.component';
import {UsersModule} from './user/user.module';
import {ListUsersComponent} from './user/listUsers/listUsers.component';
import {RestDataSource} from './service/rest.datasource';
import {HttpModule} from '@angular/http';
import {HttpClientJsonpModule, HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {SafePipe} from './pipe/SafePipe.pipes';
import {UserData} from './service/user.data';

@NgModule({
  declarations: [
    AppComponent, LoginComponent, SafePipe,
  ],
  imports: [
    BrowserModule
    , ListAllCoubsModule, UsersModule, HttpClientModule, ReactiveFormsModule, HttpModule
    , RouterModule.forRoot([
      {path: 'listUsers', component: ListUsersComponent
       // , canActivate: [StoreFirstGuard]
      },
      {path: 'listCoubs', component: ListAllCoubsComponent,
        canActivate: [StoreFirstGuard]
      },
      {path: 'user/:id', component: UserComponent
        // , canActivate: [StoreFirstGuard]
      },
      {path: 'login', component: LoginComponent
        // ,
        // canActivate: [StoreFirstGuard]
      },
      { path: '**', redirectTo: 'login' , pathMatch: 'full' }
    ])
  ],
  providers: [StaticDataSource, StoreFirstGuard, RestDataSource, UserData],
  bootstrap: [AppComponent]
})
export class AppModule { }
