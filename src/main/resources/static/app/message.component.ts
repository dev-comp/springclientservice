import {MessageService, Message} from './websocket.connect';
import {Component, ElementRef} from '@angular/core';
import {Subject} from 'rxjs/Rx'

@Component({
    selector: 'message',
    template: `
        <div class="messages">
            <p *ngFor="let msg of messages; #last = last">{{ msg.message }}</p>
        </div>
    `,
    directives: [],
    providers: [MessageService]
})
export class Chat {
    private messages: Message[] = new Array();

    constructor(private messageService: MessageService) {
        messageService.messages.subscribe(msg => {
            this.messages.push(msg);
        });
    }
}