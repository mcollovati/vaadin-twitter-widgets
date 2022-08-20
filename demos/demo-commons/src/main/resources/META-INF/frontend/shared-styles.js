import '@polymer/polymer/lib/elements/custom-style.js';
const documentContainer = document.createElement('template');

documentContainer.innerHTML = `
<custom-style>
    <!-- This is where your App's styling should go. You can use .css files too. -->
    <style>
    .tw-docs {
        font-size: 90%;
        height: 100%;
        overflow-y: auto;

        .tw-switch-out {
            @include transform-origin(50% 100%);
            @include animation(rotateFoldTop 0.5s both ease);
        }
        .tw-switch-in {
            @include animation(moveFromBottomFade 0.5s both ease);
        }
    }



    .root-container {
        padding: 10px;
    }
    .centered-caption {
        text-align: center;
        color: var(--)
    }

    .primary-text {
      color: var(--lumo-primary-text-color);
    }
    .font-size-l {
      font-size: var(--lumo-font-size-l);
    }

    .tw-demo-tab {
        margin: 10px;
    }
    .tw-demo-tab {
        margin: 10px;
        flex-wrap: wrap;
    }

    .tw-widget {
        width: 300px;
        margin: 0 20px 20px 20px;
    }

    .tw-timeline {
        overflow-y: scroll;
    }

    .tw-single-tweet {
        min-height: 250px;
    }

    .v-label-undef-w {
        white-space: pre-wrap;
    }

    </style>
</custom-style>`;

document.head.appendChild(documentContainer.content);