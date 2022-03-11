import { html, LitElement } from 'lit';
import {customElement, property} from 'lit/decorators.js';
import './tws-loader.js';
import {TwsCommonsMixin} from './tws-common.js';

// Rendering button on shadow DOM does not work well with Chrome:
// the rendered iframe has size 0 and is not visible
// as a workaround the button is rendered in a DIV
// supplied by as slot item
@customElement('tws-button')
class TwsButton extends TwsCommonsMixin(LitElement) {
  render() {
    return html`
   <slot>
    <div></div>
   </slot> 
`;
  }

  static get optsNames() {
      return [
          "align", "count", "size", "text",
          { key: "showScreenName", map: function(val) { return (val === false) ? false : true; }}
      ];
  }

  @property()
  align?: string;

  @property()
  text?: string;

  @property()
  count?: string;

  @property()
  size?: string;

  @property()
  buttonType?: string;

  @property()
  showScreenName: Boolean;

  previewTemplate() {
      const previewTextMapper = {
          follow: function(state) { return "Follow @" + state.primaryArgument; },
          share: function(state) { return "Tweet"; },
          mention: function(state) { return "Tweet @" + state.primaryArgument; },
          hashtag: function(state) { return "Tweet #" + state.primaryArgument; },
      };
    const buttonType = this.buttonType.toLowerCase();

    const href = "https://twitter.com/" + ((buttonType === "follow") ? this.primaryArgument : "/intent/tweet");

    return html`<a href="${href}">${previewTextMapper[buttonType]}</a>`;
  }

    render() {
        return html``;
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

