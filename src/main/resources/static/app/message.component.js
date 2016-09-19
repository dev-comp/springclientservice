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
var websocket_connect_1 = require('./websocket.connect');
var core_1 = require('@angular/core');
var Chat = (function () {
    function Chat(messageService) {
        var _this = this;
        this.messageService = messageService;
        this.messages = new Array();
        messageService.messages.subscribe(function (msg) {
            _this.messages.push(msg);
        });
    }
    Chat = __decorate([
        core_1.Component({
            selector: 'message',
            template: "\n        <div class=\"messages\">\n            <p *ngFor=\"let msg of messages; #last = last\">{{ msg.message }}</p>\n        </div>\n    ",
            directives: [],
            providers: [websocket_connect_1.MessageService]
        }), 
        __metadata('design:paramtypes', [websocket_connect_1.MessageService])
    ], Chat);
    return Chat;
}());
exports.Chat = Chat;
//# sourceMappingURL=message.component.js.map