import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'http-connect',
    templateUrl: './app/app.component.user.message.html'/*,
    styleUrls:  ['./app/http.component.css']*/

})

export class UserMessages implements OnInit {
    constructor() {};

    ngOnInit() {
        alert('her');
    }
}