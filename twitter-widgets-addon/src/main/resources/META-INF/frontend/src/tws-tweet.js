import { html, LitElement } from 'lit';
import './tws-loader.js';
import {TwsCommonsMixin} from './tws-common.js';

class TwsTweet extends TwsCommonsMixin(LitElement) {

    static get properties() {
        return {
            align: { type: String },
            conversation: { type: String },
            cards: { type: String },
            linkColor: { type: String },
            theme: { type: String }
        }
    }

    __createWidget(element, opts) {
        element.querySelectorAll('*').forEach(el => el.remove());
        return window.twttr.widgets.createTweet(
          this.primaryArgument, element, opts
        );//.then(el => console.log("Tweet loaded", el));
    }

}
customElements.define('tws-tweet', TwsTweet);

