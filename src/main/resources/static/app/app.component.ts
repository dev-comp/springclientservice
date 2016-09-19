import {Component} from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';
import {HTTPComponent} from './http.component';

@Component({
    selector: 'dev-comp',
    templateUrl: './app/app.component.html',
    styleUrls:  ['./app/app.component.css'],
    providers:  [HTTP_PROVIDERS],
    directives: [HTTPComponent]
})
export class AppComponent { 

    constructor() {

    }

}
