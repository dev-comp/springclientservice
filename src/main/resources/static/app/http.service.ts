import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';

@Injectable()
export class HTTPService {
    constructor(private _http: Http) {}

    private _sUrlGet = '/userlist';
    getUsers() {
        return this.getDataFromService(this._sUrlGet);   
    }

    getDataFromService(addr: any) {   
        let srv = this._http.get(addr); 
        return srv
            .map(res => res.json())
            .catch(this.handleError);
    }

    getDataFromServiceTxt(addr: any) {   
        let srv = this._http.get(addr); 
        return srv
            .map(res => res)
            .catch(this.handleError);
    }

    sendToService(body: any, addr: any) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        
        let srv = this._http.post(addr, body, {
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