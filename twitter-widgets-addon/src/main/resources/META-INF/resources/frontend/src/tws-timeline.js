import { PolymerElement } from '@polymer/polymer/polymer-element.js';
import './tws-loader.js';
import {TwsCommonsMixin} from './tws-common.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';
const datasourceGenerators = {
    profile: function(ds, state) {
        ds.screenName = state.primaryArgument;
    },
    likes: function(ds, state) {
        ds.screenName = state.primaryArgument;
    },
    collection: function(ds, state) {
        ds.id = state.primaryArgument;
    },
    list: function(ds, state) {
        var m = state.primaryArgument.match(/^([^@]+)@(.+)$/);
        if (m) {
            ds.ownerScreenName = m[2];
            ds.slug = m[1];
        } else {
            ds.id = state.primaryArgument;
        }
    },
    url: function(ds, state) {
        ds.url = state.primaryArgument;
    },
    widget: function(ds, state) {
        ds.widgetId = state.primaryArgument;
    }
};
const createDataSource = function(state) {
    var ds = {
        sourceType: state.datasource
    };
    datasourceGenerators[ds.sourceType](ds, state);
    return ds;
};

class TwsTimeline extends TwsCommonsMixin(PolymerElement) {
  static get template() {
    return html`
   <div id="twsContainer"></div> 
`;
  }

  constructor() {
      super();
  }

  static get is() {
      return 'tws-timeline'
  }

  static get optsNames() {
      return [
          "ariaPolite", "borderColor", "tweetLimit",
          { key: "chrome", map: val => { return this._arrayToStringList(val) || null; } }
      ];
  }

  static get properties() {
      return {
          datasource: String,
          ariaPolite: String,
          borderColors: String,
          chrome: Array,
          tweetLimit: Number
      }
  }

  _render(opts) {
      if (this.$.twsContainer.clientHeight > 0) {
          opts.height = this.$.twsContainer.clientHeight;
      };
       window.twttr.widgets.createTimeline(
          createDataSource(this), this.$.twsContainer, opts)
      .then(function (el) {
          console.log("Timeline loaded", el);
      });
  }

  ready() {
      super.ready();
      window.twttr.ready(() => this._render());
  }
}
customElements.define(TwsTimeline.is, TwsTimeline);

