import {RouterModule} from '@angular/router';
import {StoreFirstGuard} from '../storeFirst.guard';
import {AppComponent} from '../app.component';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ListAllCoubsComponent} from './listAllCoubs.component';


@NgModule({
  declarations: [
    ListAllCoubsComponent
  ],
  imports: [
    BrowserModule, RouterModule
    ],
  providers: [],
  bootstrap: [ListAllCoubsComponent],
  exports: [ListAllCoubsComponent],
  entryComponents: [ListAllCoubsComponent]
})

export class ListAllCoubsModule { }
