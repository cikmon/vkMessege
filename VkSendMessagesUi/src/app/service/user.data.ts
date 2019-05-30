///<reference path="rest.datasource.ts"/>
import {Injectable, OnInit} from '@angular/core';
import { Http, Request, RequestMethod } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../model/user.model';

import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';
import {RestDataSource} from './rest.datasource';
import {log} from 'util';



@Injectable()
export class UserData  {

  private users: Array<User>;

  constructor(private restData: RestDataSource) {
    this.restData.getUsers().subscribe((data) => this.users = data );
  }

  getUsers(): Observable <User[]> {return this.restData.getUsers(); }
  setUser(user: User) {this.restData.setUser(user).subscribe(); }

  getUserById(id: String): User {return this.users.filter(i => i.id ===  id)[0]; }
}
