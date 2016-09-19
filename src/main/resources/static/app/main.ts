import {bootstrap}    from '@angular/platform-browser-dynamic';
import {AppComponent} from './app.component';
//import { WebsocketAppComponent }  from './websocket.service.main.component';
import {HTTP_PROVIDERS} from '@angular/http';

bootstrap(AppComponent/*WebsocketAppComponent*/, HTTP_PROVIDERS);
