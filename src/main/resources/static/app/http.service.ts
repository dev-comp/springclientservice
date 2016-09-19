import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';

@Injectable()
export class HTTPService {
    constructor(private _http: Http) {}

    private _sUrl = 'http://localhost:8080/hey';
    getUsers() {   
        let srv = this._http.get(this._sUrl); 
        return srv
            .map(res => res.json())
            .catch(this.handleError);
    }
    private handleError (error: Response) {
        console.log(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}