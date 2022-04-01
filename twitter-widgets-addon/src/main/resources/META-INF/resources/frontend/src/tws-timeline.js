import { html, LitElement } from 'lit';
import './tws-loader.js';
import {TwsCommonsMixin} from './tws-common.js';

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

class TwsTimeline extends TwsCommonsMixin(LitElement) {

    static get properties() {
        return {
            datasource: { type: String },
            ariaPolite: { type: String },
            borderColors: { type: String },
            chrome: { type: Array },
            tweetLimit: { type: Number }
        }
    }

    __createWidget(element, opts) {
        return window.twttr.widgets.createTimeline(createDataSource(this), element, opts);
        /*
        .then(function (el) {
          console.log("Timeline loaded", el);
        });
        */
    }
}
customElements.define('tws-timeline', TwsTimeline);

