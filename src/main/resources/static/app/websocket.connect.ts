import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs/Rx';
import {WebSocketService} from './websocket.service';

const _URL = 'ws://localhost:8080/dev-comp';

export interface Message {
    author: string;
    message: string;
}

@Injectable()
export class MessageService {
    public messages: Subject<Message>;

    constructor(wsService: WebSocketService) {

        this.messages = <Subject<Message>>wsService
            .connect(_URL)
            .map((response: MessageEvent): Message => {
                let data = JSON.parse(response.data);
                return {
                    author: data.author,
                    message: data.message,
                }
            });
    }
}