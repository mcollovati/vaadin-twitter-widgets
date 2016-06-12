window.org_vaadin_addon_twitter_TweetButton = function() {
    var me = this;



    var createPreviewElement = function() {

        var previewTextMapper = {
            follow: function(state) { return "Follow @" + state.primaryArgument; },
            share: function(state) { return "Tweet"; },
            mention: function(state) { return "Tweet @" + state.primaryArgument; },
            hashtag: function(state) { return "Tweet #" + state.primaryArgument; },
        };

        var buttonType = me.getState().buttonType.toLowerCase();
        var el = document.createElement("A");
        el.className = "twitter-" + buttonType + "-button";
        if (buttonType === "follow") {
            el.href = "https://twitter.com/" + me.getState().primaryArgument;
        } else {
            el.href = "https://twitter.com/intent/tweet";
        }
        el.innerText = previewTextMapper[buttonType](me.getState());
        return el;
    }
    var optsNames = ["align", "count", "size", "text",
                     { key: "showScreenName", map: function(val) { return (val === false) ? false : true; }}];

    var createOptions = function(state) {
        var opts = window.vaadinTwttr.mergeOptions(state, optsNames);
        return opts;
    }

    var createButton = function() {
        var state = me.getState();
        var generatorFn = "create" + state.buttonType + "Button";
        window.twttr.widgets[generatorFn](state.primaryArgument, me.getElement(), createOptions(state))
        .then(function (el) {
            // TODO: callback to java component?
            me.getElement().removeChild(me.previewElement);
            delete me.previewElement;
        });
    }


    me.previewElement = createPreviewElement();
    me.getElement().appendChild(me.previewElement);
    me.onStateChange = function() {
        window.twttr.ready(createButton);
    };
};