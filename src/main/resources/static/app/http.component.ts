import {Component} from '@angular/core';
import {HTTPService} from './http.service';
import {User} from './app.component.user';

@Component({
    selector: 'http-connect',
    templateUrl: './app/http.component.html',
    styleUrls:  ['./app/http.component.css'],
    providers: [HTTPService]

})

export class HTTPComponent {
    getData: String;
    users: User[];
    items: number[];
    postStatus: String;
    

    constructor(private _httpService: HTTPService) {
        this.users = [];
        this.items = [];
    }

    onTestGet() {
        this.items = [];
        this._httpService.getUsers()
            .subscribe(
                user => this.users = <User[]>user,
                error => alert(error),
                () => console.log("Finished")
            );
            
    }

    addItem(item: number, isChecked: boolean) {
        if (isChecked) {
            this.items.push(item);
        } else {
            for (var i = 0; i < this.items.length; i++) {
                if (this.items[i] == item) {         
                    this.items.splice(i, 1);
                    return;
                }
            }        
        }
    }

    clearMessage(text: any) {
        text.value = "";
    }

    sendMessage(text: any) {
        this.postStatus = '';
        let obj = JSON.stringify({"userIds":this.items,"msgBody":text.value});

        this._httpService.sendToService(obj)
        .subscribe(
                postStatus => this.postStatus = postStatus,
                error => alert(error),
                () => console.log("Finished")
            );
        text.value = '';
    }

}