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
var http_service_1 = require('./http.service');
var HTTPComponent = (function () {
    function HTTPComponent(_httpService) {
        this._httpService = _httpService;
        this._sUrlPost_sendMsg = '/sendMessage';
        this._sUrlPost_getMsg = '/userKeyLog';
        this.users = [];
        this.items = [];
    }
    HTTPComponent.prototype.onGetUsers = function () {
        var _this = this;
        this.items = [];
        this._httpService.getUsers()
            .subscribe(function (user) { return _this.users = user; }, function (error) { return alert(error); }, function () { return console.log("Finished"); });
    };
    HTTPComponent.prototype.addItem = function (item, isChecked) {
        if (isChecked) {
            this.items.push(item);
        }
        else {
            for (var i = 0; i < this.items.length; i++) {
                if (this.items[i] == item) {
                    this.items.splice(i, 1);
                    return;
                }
            }
        }
    };
    HTTPComponent.prototype.clearMessage = function (text) {
        text.value = "";
    };
    HTTPComponent.prototype.sendMessage = function (text) {
        var _this = this;
        this.postStatus = false;
        var obj = JSON.stringify({ "userIds": this.items, "msgBody": text.value });
        this._httpService.sendToService(obj, this._sUrlPost_sendMsg)
            .subscribe(function (postStatus) {
            _this.postStatus = postStatus;
            if (postStatus) {
                text.value = '';
            }
            else {
                alert('Ошибка при отправки сообщения');
            }
        }, 
        //postStatus => this.postStatus = postStatus,
        function (error) { return alert(error); }, function () { return console.log("Finished"); });
    };
    HTTPComponent.prototype.onKeyUp = function (btnSend, btnClear, editText) {
        btnSend.disabled = editText.value.length == 0;
        btnClear.disabled = editText.value.length == 0;
    };
    HTTPComponent.prototype.saveLinkUser = function (usr, messageStory) {
        var _this = this;
        //this.historyMsg = "";
        if (!this.linkUserHistory) {
            this.linkUserHistory = usr;
            this._httpService.getDataFromServiceTxt(this._sUrlPost_getMsg + '?id=' + usr.id)
                .subscribe(function (msg) {
                _this.historyMsg = msg;
            }, function (error) { return alert(error); }, function () { return console.log("Finished"); });
        }
        else {
            this.linkUserHistory = null;
        }
    };
    HTTPComponent = __decorate([
        core_1.Component({
            selector: 'http-connect',
            templateUrl: './app/http.component.html',
            styleUrls: ['./app/http.component.css'],
            providers: [http_service_1.HTTPService]
        }), 
        __metadata('design:paramtypes', [http_service_1.HTTPService])
    ], HTTPComponent);
    return HTTPComponent;
}());
exports.HTTPComponent = HTTPComponent;
//# sourceMappingURL=http.component.js.map