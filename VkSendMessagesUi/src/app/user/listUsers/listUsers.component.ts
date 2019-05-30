import {Router} from '@angular/router';
import {Component, EventEmitter, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {User} from '../../model/user.model';
import {ArrayType} from '@angular/compiler';
import {StaticDataSource} from '../../service/static.data';
import {RestDataSource} from '../../service/rest.datasource';
import {Coub} from '../../model/coub.model';
import {Observable} from 'rxjs';
import {log} from 'util';
import {UserData} from '../../service/user.data';

@Component({
  selector: 'app-userlist',
  templateUrl: './listUsers.component.html',
  styleUrls: ['./listUsers.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ListUsersComponent implements OnInit {

  users: Observable <User[]>;
  constructor( private router: Router, private  userData: UserData) {
  }
  ngOnInit() {
    this.users = this.userData.getUsers();
  }






}
