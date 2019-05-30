import {NgModule} from '@angular/core';
import {ListAllCoubsComponent} from '../ListAllCoubs/listAllCoubs.component';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {UserComponent} from './user.component';
import {ListUsersComponent} from './listUsers/listUsers.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    UserComponent, ListUsersComponent
  ],
  imports: [
    BrowserModule, RouterModule, FormsModule
  ],
  providers: [],
  bootstrap: [ListUsersComponent],
  exports: [ListUsersComponent],
  entryComponents: [ListUsersComponent]
})

export class UsersModule {}
