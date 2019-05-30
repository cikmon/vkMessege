import { Injectable } from '@angular/core';
import { Http, Request, RequestMethod } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../model/user.model';

import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';
import {Coub} from '../model/coub.model';
import {log} from 'util';

const PROTOCOL = 'http';
const PORT = 3500;


@Injectable()
export class RestDataSource {

  constructor(private http: HttpClient) {
  }

  baseUrl = 'http://localhost:8080/';
  login(loginPayload) {
    const headers = {
      'Authorization': 'Basic ' + btoa('devglan-client:devglan-secret'),
      'Content-type': 'application/x-www-form-urlencoded'
    };
    return this.http.post('http://localhost:8080/' + 'oauth/token', loginPayload, {headers});
  }

  getCoubs(): Observable<Coub[]> {
    return this.http.get<Coub[]>(this.baseUrl + 'getCoubsRepository?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }
  getUsers(): Observable<User[]> { return this.http.get<User[]>(this.baseUrl +'getUsers?access_token=' +JSON.parse(window.sessionStorage.getItem('token')).access_token); }
  setUser(user: User) { return this.http.post<User>(this.baseUrl +'updateUser?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token
    , user); }


  // getUsers(): Observable<User[]> { return this.http.get<User[]>('http://localhost:8080/getUsers'); }
  // setUser(user: User) { return this.http.post<User>('http://localhost:8080/updateUser', user); }
  // getCoubs(): Observable<Coub[]> { return this.http.get<Coub[]>('http://localhost:8080/getCoubsRepository'); }
}

