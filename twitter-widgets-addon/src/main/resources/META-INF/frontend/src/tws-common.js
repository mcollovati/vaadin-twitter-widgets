import { html, css } from 'lit';

export const TwsCommonsMixin = (superClass) => class extends superClass {

    static get properties() {
        return {
            primaryArgument: {type: String},
            dnt: { type: Boolean },
            lang: { type: String },
            hashtags: { type: Array },
            related: { type: Array },
            via: { type: String }
        }
    }

    static get styles() {
        return css`
            :host {
                --loader-color: rgb(142, 16, 16);
            }
            .lds-dual-ring {
              display: inline-block;
              width: 40px;
              height: 40px;
            }
            .lds-dual-ring:after {
              content: " ";
              display: block;
              width: 32px;
              height: 32px;
              margin: 4px;
              border-radius: 50%;
              border: 2px solid var(--loader-color);
              border-color: var(--loader-color) transparent #fff transparent;
              animation: lds-dual-ring 1.2s linear infinite;
            }
            @keyframes lds-dual-ring {
              0% {
                transform: rotate(0deg);
              }
              100% {
                transform: rotate(360deg);
              }
            }
             #preview {
                 width: 100%;
                 text-align: center;
             }
        `
    }

    // Rendering tweet on shadow DOM does not work well with Chrome:
    // the rendered iframe has size 0 and is not visible
    // as a workaround the button is rendered in a DIV
    // supplied by as slot item
    render() {
        return html`
            <slot></slot><div id="preview">${this.__previewTemplate()}</div>
        `;
    }

    __widgetLoaded() {
        this.preview = this.shadowRoot.querySelector("#preview");
        this.shadowRoot.removeChild(this.preview);
    }

    __previewTemplate() {
        return html`<div class="lds-dual-ring"></div>`
    }
    __applyOptions() {
        const applyConversion = (obj) => {
            if (Array.isArray(obj)) {
                return (obj || []).join(",");
            }
            return obj;
        }
        const opts = {};
        this.constructor.elementProperties.forEach( (type, key) => {
            if (!key.startsWith("_")) {
                const val = applyConversion(this[key]);
                if (val) {
                    opts[key] = val;
                }
            }
        });
        return opts;
    }

    updated(changedProperties) {
        if (this.preview) {
            this.shadowRoot.appendChild(this.preview);
        }
        const element = this.shadowRoot.querySelector('slot').assignedElements()[0];
        window.twttr.ready(() => {
            this.__createWidget(element, this.__applyOptions())
              .then(el => {
                this.__widgetLoaded();
                // TODO: fire server event
              }, err => {
                console.log("Error loading button", err);
                // TODO: fire server event
              });

        });
    }

}

