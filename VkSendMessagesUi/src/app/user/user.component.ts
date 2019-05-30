import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {StaticDataSource} from '../service/static.data';
import {User} from '../model/user.model';
import {Import} from '@angular/compiler-cli/src/ngtsc/host';
import {log} from 'util';
import {forEach} from '@angular/router/src/utils/collection';
import {RestDataSource} from '../service/rest.datasource';
import {UserData} from '../service/user.data';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],

})
export class UserComponent  {

  constructor( private router: Router, private activateRoute: ActivatedRoute, private userData: UserData ) {

    this.id = activateRoute.snapshot.params['id'];
    this.user = userData.getUserById(String(this.id));
    this.userUpdate = this.user;
  }
  id: number;
  user: User;
  userUpdate: User;
  edit = false;

 private viewItemList = 0;



 public editForm (edit: boolean) {
    this.edit = edit;
 }
  public getviewItemListt(): number {
    return this.viewItemList;
  }

  public setviewItemListt(value: number) {
    if (value === this.viewItemList) {this.viewItemList = 0; } else { this.viewItemList = value; }
  }


  public exit() {
    this.userUpdate = this.user;
    this.editForm(false);
  }

  public save() {
    this.user = this.userUpdate;
    this.userData.setUser(this.userUpdate);
    this.editForm(false);
  }




  public removeTypeCoub(type: String) {
    this.userUpdate.blockTypeCoubs = this.userUpdate.blockTypeCoubs.filter(i => i !== type);
  }

  public addTypeCoub(type: String) {
   if ( type == null || type === '') {return null; }
   this.userUpdate.blockTypeCoubs.forEach(i => { if (i === type) {return null; }});
    this.userUpdate.blockTypeCoubs.push(type);
  }

  public saveIconUrl(url: String) {
    if ( url == null || url === '' || this.userUpdate.icon === url) {return null; }
    this.userUpdate.icon = url;
    this.save();
  }

  public onEnter(value: string) {this.addTypeCoub(value); }

  public onEnterIconEditUrl(value: string ) {
   this.saveIconUrl(value);
   }

}
