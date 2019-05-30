import {Injectable} from '@angular/core';
import {User} from '../model/user.model';
import {log} from 'util';
import {RestDataSource} from './rest.datasource';

@Injectable()
export class StaticDataSource {


 public dataCoub = ['1', '3', '4','5','6','7','8','9','10'];

  // user: User = new User('1', true, 'name1', 1111, 2,
  //   'https://sun6-2.userapi.com/c834202/v834202937/93fb6/nxF4rFLKdB0.jpg?ava=1',
  //   ['rrr', 'tttt', 'fff'], ['rrr2', 'tttt2', 'fff2'], ['rrr3', 'tttt3', 'fff3'],
  //   'gggg');
  //
  // user2: User = new User('2', true, 'name2', 11113, 23,
  //   'https://sun6-4.userapi.com/c846320/v846320081/160c6b/xwiovxWIdQU.jpg?ava=1',
  //   ['rrr3', 'tttt3', 'fff3', 'rrr3', 'tttt3', 'fff3', 'rrr3', 'tttt3', 'fff3', 'rrr3', 'tttt3', 'fff3'],
  //   ['rrr32', 'tttt32', 'fff32'], ['rrr33', 'tttt33', 'fff33'],
  //   '3gggg');

  private _userData: User[] = [  ];


  getUserData(): User[] {
    return this._userData;
  }

  getUserById(id: String): User {
    return this._userData.find(u => u.id === id);
  }
    updateUser(user: User){
    let  ind: number;
       ind ==  this._userData.findIndex(u => u.id === user.id);
       this._userData[ind] = user;
    }



}
