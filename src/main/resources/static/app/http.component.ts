import {Component} from '@angular/core';
import {HTTPService} from './http.service';
import {User} from './app.component.user';
import {Response} from '@angular/http';

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
    postStatus: boolean;
    linkUserHistory: User;
    historyMsg: Response;
    

    constructor(private _httpService: HTTPService) {
        this.users = [];
        this.items = [];
    }

    onGetUsers() {
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

    private _sUrlPost_sendMsg = '/sendMessage';
    sendMessage(text: any) {
        this.postStatus = false;
        let obj = JSON.stringify({"userIds":this.items,"msgBody":text.value});
        this._httpService.sendToService(obj, this._sUrlPost_sendMsg)
        .subscribe(
                (postStatus: boolean) => {
                    this.postStatus = postStatus;
                    if (postStatus) {
                      text.value = '';
                    } else {
                      alert('Ошибка при отправки сообщения');  
                    }
                },
                //postStatus => this.postStatus = postStatus,
                error => alert(error),
                () => console.log("Finished")
            );
        
    }

    private _sUrlPost_getMsg = '/userKeyLog';
    saveLinkUser(usr: User, messageStory: any) {
        //this.historyMsg = "";
        if (!this.linkUserHistory) {
          this.linkUserHistory = usr;

            this._httpService.getDataFromServiceTxt(this._sUrlPost_getMsg + '?id=' + usr.id)
                .subscribe(
                    (msg: Response) => {
                        this.historyMsg = msg;
                    },
                    error => alert(error),
                    () => console.log("Finished")
                );


        } else {
          this.linkUserHistory = null;
        }  

    }


}