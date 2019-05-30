import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {StaticDataSource} from '../service/static.data';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user.model';
import {RestDataSource} from '../service/rest.datasource';
import {Observable} from 'rxjs';
import {Coub} from '../model/coub.model';
import {DomSanitizer} from '@angular/platform-browser';
import {logger} from 'codelyzer/util/logger';
import {log} from 'util';

interface Course {
  id: string;
  description: string;
  courseListIcon: string;
  iconUrl: string;
  longDescription: string;
  url: string;
}

@Component({
  selector: 'app-coubs-list',
   templateUrl: './listAllCoubs.component.html',
  styleUrls: ['./listAllCoubs.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ListAllCoubsComponent implements OnInit {

  coubs: Observable<Coub[]>;


  constructor( private router: Router, private restData: RestDataSource, private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    this.coubs = this.restData.getCoubs();
  }

  coubUrl(id: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(
      'https://coub.com/embed/' + id + '?autostart=false&muted=false&originalSize=false&startWithHD=false');
  }



}
