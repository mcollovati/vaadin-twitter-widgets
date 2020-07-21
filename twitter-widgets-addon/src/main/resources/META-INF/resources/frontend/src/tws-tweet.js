import { PolymerElement } from '@polymer/polymer/polymer-element.js';
import './tws-loader.js';
import {TwsCommonsMixin} from './tws-common.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';
class TwsTweet extends TwsCommonsMixin(PolymerElement) {
  static get template() {
    return html`
   <style>
     #twsContainer {
         width: 100%;
         height: 100%;
         overflow: auto;
     }
    </style>
   <div id="twsContainer"></div>
`;
  }

  constructor() {
      super();
  }

  static get is() {
      return 'tws-tweet'
  }

  static get optsNames() {
      return ["align", "conversation", "cards", "linkColor", "theme"];
  }

  static get properties() {
      return {
          conversation: String,
          cards: String,
          linkColor: String,
          theme: String
      }
  }

  _render(opts) {
      this.$.twsContainer.querySelectorAll('*').forEach(el => el.remove());

      // Workaround to make iframe visible
      // twitter widget fails to load inside shadow dom
      // so we reset iframe style once added to parent container
      const h = (this.$.twsContainer.clientHeight || 300)+'px';
      const w = (this.$.twsContainer.clientWidth || 250)+'px';

      const observer = new MutationObserver(function(mutations) {
        mutations.filter(m => (m.addedNodes || []).length > 0)
            .map( m => m.addedNodes[0].querySelector("iframe"))
            .forEach(i => {
                i.style.visibility = 'visible';
                i.style.position = 'relative';
                i.style.height = h;
                i.style.width = w;
            });
        observer.disconnect();
      });
      const config = { attributes: false, childList: true, characterData: false };
      observer.observe(this.$.twsContainer, config);

      window.twttr.widgets.createTweet(
          this.primaryArgument, this.$.twsContainer, opts
      ).then(el => console.log("Tweet loaded", el));
  }

  ready() {
      super.ready();
  }


}
customElements.define(TwsTweet.is, TwsTweet);

