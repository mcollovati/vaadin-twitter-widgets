import { idlePeriod } from '@polymer/polymer/lib/utils/async.js';
import {Debouncer} from '@polymer/polymer/lib/utils/debounce.js';

export const TwsCommonsMixin = (superClass) => class extends superClass {

    constructor() {
        super();
        this._commonOptsNames = [
            { key: "doNotTrack", remapKey: "dnt" },
            { key: "language", remapKey: "lang" },
            { key: "hashtags", map: this._arrayToStringList },
            { key: "related", map: this._arrayToStringList },
            "via"
        ];
        this.__createOptionsProperty();
    }

    static get optsNames() {
        return [];
    }

    static get properties() {
        return {
            primaryArgument: String,
            doNotTrack: Boolean,
            language: String,
            hashtags: Array,
            related: Array,
            via: String
        }
    }

    static get observers() {
        return [
            'optionsChanged(options)'
        ]
    }

    _identityFn(a) { return a; }

    _arrayToStringList (arr) { return (arr || []).join(","); }

    __applyOptions(opts) {
        opts = opts || {};
        const optsNames = this._commonOptsNames.concat(this._optsNames || []);
        optsNames.forEach(optObj => {
            const key = optObj.key || optObj;
            const remappedKey = optObj.remapKey || key;
            const mapFn = optObj.map || this._identityFn;
            if (opts.hasOwnProperty(key) && (opts[key] === false || opts[key])) {
                opts[remappedKey] = mapFn(opts[key]);
            }
        });
        return opts;
    }

    // Render the widget whenever an option has changed
    optionsChanged(opts) {
        this._debouncer = Debouncer.debounce(
            this._debouncer, // initially undefined
            idlePeriod,
            () => window.twttr.ready(() => this._render(this.__applyOptions(opts)))
        );
    }

    __buildOptions(optsNames, optsValues) {
        const opts = {};
        optsNames.forEach( (el, idx) => opts[el] = optsValues[idx] );
        return opts;
    }

    // Dynamically create a property 'options` as an object that
    // contains all properties whose value change should trigger
    // a rendering action
    __createOptionsProperty() {
        const propNames = this._commonOptsNames.concat(this.constructor.optsNames || {}).map(o => o.key || o );
        this['_computeOptionsProperty'] = Function.apply(this,
            propNames.concat([
            `return this.__buildOptions([${propNames.map(e => `'${e}'`).join(',')}], Array.from(arguments));`
        ]));
        const optionsComputedPropertyFn = `_computeOptionsProperty(${propNames.join(',')})`;
        this._createComputedProperty('options', optionsComputedPropertyFn, true);

    }

}

