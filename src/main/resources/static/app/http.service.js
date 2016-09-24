"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
var Observable_1 = require('rxjs/Observable');
require('rxjs/Rx');
var HTTPService = (function () {
    function HTTPService(_http) {
        this._http = _http;
        this._sUrlGet = '/userlist';
        this._sUrlPost = '/sendMessage';
    }
    HTTPService.prototype.getUsers = function () {
        var srv = this._http.get(this._sUrlGet);
        return srv
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    HTTPService.prototype.sendToService = function (body) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var srv = this._http.post(this._sUrlPost, body, {
            headers: headers
        });
        return srv
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    HTTPService.prototype.handleError = function (error) {
        console.log(error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    HTTPService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], HTTPService);
    return HTTPService;
}());
exports.HTTPService = HTTPService;
//# sourceMappingURL=http.service.js.map