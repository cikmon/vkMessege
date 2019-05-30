///<reference path="../../../node_modules/@angular/http/src/http.d.ts"/>
import { Injectable } from '@angular/core';
import { Http, Request, RequestMethod } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { HttpClient } from '@angular/common/http';
import {Admin} from '../admin/admin.model';
import {log} from "util";

const PROTOCOL = 'http';
const PORT = 8080;



export class RestData {
  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://jsonplaceholder.typicode.com/users';

  login(loginPayload) {
    const headers = {
      'Authorization': 'Basic ' + btoa('devglan-client:devglan-secret'),
      'Content-type': 'application/x-www-form-urlencoded'
    }
    return this.http.post('http://localhost:8080/' + 'oauth/token', loginPayload, {headers});
  }

  getUsers() {
    log(this.http.get(this.baseUrl));
    return this.http.get(this.baseUrl);
  }




}

