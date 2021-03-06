import { PolymerElement } from '@polymer/polymer/polymer-element.js';
import './tws-loader.js';
import {TwsCommonsMixin} from './tws-common.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';

// Rendering button on shadow DOM does not work well with Chrome:
// the rendered iframe has size 0 and is not visible
// as a workaround the button is rendered in a DIV
// supplied by as slot item
class TwsButton extends TwsCommonsMixin(PolymerElement) {
  static get template() {
    return html`
   <slot>
    <div></div>
   </slot> 
`;
  }

  constructor() {
      super();
  }

  static get is() {
      return 'tws-button'
  }

  static get optsNames() {
      return [
          "align", "count", "size", "text",
          { key: "showScreenName", map: function(val) { return (val === false) ? false : true; }}
      ];
  }

  static get properties() {
      return {
          align: String,
          text: String,
          count: String,
          size: String,
          buttonType: String,
          showScreenName: Boolean
      }
  }


  _createPreviewElement() {

      const previewTextMapper = {
          follow: function(state) { return "Follow @" + state.primaryArgument; },
          share: function(state) { return "Tweet"; },
          mention: function(state) { return "Tweet @" + state.primaryArgument; },
          hashtag: function(state) { return "Tweet #" + state.primaryArgument; },
      };

      const buttonType = this.buttonType.toLowerCase();
      const el = document.createElement("A");
      el.className = 'twitter-' + buttonType + '-button';
      if (buttonType === "follow") {
          el.href = "https://twitter.com/" + this.primaryArgument;
      } else {
          el.href = "https://twitter.com/intent/tweet";
      }
      el.innerText = previewTextMapper[buttonType](this);
      return el;
  }

  _render(opts) {
      const generatorFn = "create" + this.buttonType + "Button";
      window.twttr.widgets[generatorFn](this.primaryArgument, this.firstElementChild, opts)
      .then(el => {
          //Polymer.dom(this.root).removeChild(this.__previewElement)
          this.root.removeChild(this.__previewElement)
          delete this.__previewElement;
          // TODO: fire event?
      }, err => console.log("Error loading button", err));
  }

  ready() {
      super.ready();
      this.__previewElement = this._createPreviewElement();
      //Polymer.dom(this.root).appendChild(this.__previewElement)
      this.root.appendChild(this.__previewElement);
  }
}
customElements.define(TwsButton.is, TwsButton);

