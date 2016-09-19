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
var websocket_service_main_1 = require('./websocket.service.main');
var WebsocketAppComponent = (function () {
    function WebsocketAppComponent() {
        this.counter = 'not known';
        this.ws = new websocket_service_main_1.$WebSocket("ws://localhost:8088/counter");
    }
    WebsocketAppComponent.prototype.subscribe = function ($event) {
        var _this = this;
        console.log("trying to subscribe to ws");
        this.ws = new websocket_service_main_1.$WebSocket("ws://localhost:8088/counter");
        this.ws.send("Hello");
        this.ws.getDataStream().subscribe(function (res) {
            var count = JSON.parse(res.data).value;
            console.log('Got: ' + count);
            _this.counter = count;
        }, function (e) { console.log('Error: ' + e.message); }, function () { console.log('Completed'); });
    };
    WebsocketAppComponent = __decorate([
        core_1.Component({
            selector: 'dev-comp',
            template: "\n\tCounter Value is: {{counter}}\n\t<button type=\"button\" (click)=\"subscribe($event)\">Subscribe to WebSocket</button>\n\t"
        }), 
        __metadata('design:paramtypes', [])
    ], WebsocketAppComponent);
    return WebsocketAppComponent;
}());
exports.WebsocketAppComponent = WebsocketAppComponent;
/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
//# sourceMappingURL=websocket.service.main.component.js.map