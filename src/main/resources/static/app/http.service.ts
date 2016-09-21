import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';

@Injectable()
export class HTTPService {
    constructor(private _http: Http) {}

    private _sUrlGet = 'http://localhost:8080/userlist';
    getUsers() {   
        let srv = this._http.get(this._sUrlGet); 
        return srv
            .map(res => res.json())
            .catch(this.handleError);
    }

    private _sUrlPost = 'http://localhost:8080/sendMessage';
    sendToService(body: any) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        
        let srv = this._http.post(this._sUrlPost, body, {
            headers: headers
        }); 
        return srv
            .map(res => res.json())
            .catch(this.handleError); 
    }

    private handleError (error: Response) {
        console.log(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}