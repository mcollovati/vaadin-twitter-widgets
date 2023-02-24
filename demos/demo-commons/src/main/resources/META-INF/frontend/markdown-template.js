import { html, LitElement, css } from "lit";

import {unsafeHTML} from 'lit/directives/unsafe-html.js';
import MarkdownIt from "markdown-it";
import mdhljs from 'markdown-it-highlightjs';
import hljs from "highlight.js";

const md = MarkdownIt().use(mdhljs, { inline:true })

class MarkdownElement extends LitElement {
  static get is() {
    return "markdown-element";
  }
  static get properties() {
    return {
      markdown: String,
    };
  }

  static get styles() {
    return [
      css`
        .hljs {
          display: block;
          overflow-x: auto;
          padding: 0.5em;
          color: #abb2bf;
          background: #282c34;
        }

        .hljs-comment,
        .hljs-quote {
          color: #5c6370;
          font-style: italic;
        }

        .hljs-doctag,
        .hljs-keyword,
        .hljs-formula {
          color: #c678dd;
        }

        .hljs-section,
        .hljs-name,
        .hljs-selector-tag,
        .hljs-deletion,
        .hljs-subst {
          color: #e06c75;
        }

        .hljs-literal {
          color: #56b6c2;
        }

        .hljs-string,
        .hljs-regexp,
        .hljs-addition,
        .hljs-attribute,
        .hljs-meta-string {
          color: #98c379;
        }

        .hljs-built_in,
        .hljs-class .hljs-title {
          color: #e6c07b;
        }

        .hljs-attr,
        .hljs-variable,
        .hljs-template-variable,
        .hljs-type,
        .hljs-selector-class,
        .hljs-selector-attr,
        .hljs-selector-pseudo,
        .hljs-number {
          color: #d19a66;
        }

        .hljs-symbol,
        .hljs-bullet,
        .hljs-link,
        .hljs-meta,
        .hljs-selector-id,
        .hljs-title {
          color: #61aeee;
        }

        .hljs-emphasis {
          font-style: italic;
        }

        .hljs-strong {
          font-weight: bold;
        }

        .hljs-link {
          text-decoration: underline;
        }
      `,
    ];
  }
  render() {
    return html`${unsafeHTML(md.render(this.markdown))}`;
  }
  highlight(event) {
    if (hljs) {
      let code = event.detail.code;
      let highlighted = hljs.highlight(event.detail.lang, code);
      event.detail.code =
        '<div class="hljs">' + (highlighted.value || code) + "</div>";
    }
  }
}
customElements.define(MarkdownElement.is, MarkdownElement);
