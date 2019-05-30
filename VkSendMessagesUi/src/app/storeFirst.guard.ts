import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {ListAllCoubsComponent} from './ListAllCoubs/listAllCoubs.component';
import {UserComponent} from './user/user.component';
import {ListUsersComponent} from './user/listUsers/listUsers.component';

@Injectable()
export class StoreFirstGuard {
  private firstNavigation = true;
  constructor(private router: Router) { }
  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): boolean {
    if (this.firstNavigation) {
      this.firstNavigation = false;
      //if (route.component === ListUsersComponent) { return true; }
      if (route.component !== ListAllCoubsComponent) {
        this.router.navigateByUrl('/login');
        return false;
      }
    }
    return true;
  }
}
