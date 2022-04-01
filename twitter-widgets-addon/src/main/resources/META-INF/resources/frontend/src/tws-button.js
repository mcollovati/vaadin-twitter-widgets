import { html, LitElement } from 'lit';
import './tws-loader.js';
import {TwsCommonsMixin} from './tws-common.js';

class TwsButton extends TwsCommonsMixin(LitElement) {

    static get properties() {
        return {
            align: { type: String },
            text: { type: String },
            count: { type: String },
            size: { type: String },
            buttonType: { type: String },
            showScreenName: { type: Boolean },
            _loaded: { type: Boolean, state: true }
        }
    }

    __createWidget(element, opts) {
      const generatorFn = "create" + this.buttonType + "Button";
      //const root = this.shadowRoot;
      //const preview = root.querySelector("#preview");
      return window.twttr.widgets[generatorFn](this.primaryArgument, element, opts);
      /*
      .then(el => {
        this.__widgetLoaded();
        // TODO: fire server event
      }, err => {
        console.log("Error loading button", err);
        // TODO: fire server event
      });
      */
    }

  __previewTemplate() {
    const previewTextMapper = {
      follow: function(state) { return "Follow @" + state.primaryArgument; },
      share: function(state) { return "Tweet"; },
      mention: function(state) { return "Tweet @" + state.primaryArgument; },
      hashtag: function(state) { return "Tweet #" + state.primaryArgument; },
    };
    const buttonType = this.buttonType.toLowerCase();

    const href = "https://twitter.com/" + ((buttonType === "follow") ? this.primaryArgument : "intent/tweet");

    return html`<a href="${href}">${previewTextMapper[buttonType](this)}</a>`;
  }

}
customElements.define('tws-button', TwsButton);
