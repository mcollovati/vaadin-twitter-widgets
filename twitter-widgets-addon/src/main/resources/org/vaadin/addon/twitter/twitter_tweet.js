window.org_vaadin_addon_twitter_Tweet = function() {
    var me = this;
    var optsNames = ["align", "conversation", "cards", "linkColor", "theme"];

    var createOptions = function(state) {
        var opts = window.vaadinTwttr.mergeOptions(state, optsNames);
        opts.width ="auto";
        //console.log("=============== OPTS", opts);
        return opts;
    }

    var createTweet = function(state) {
        window.twttr.widgets.createTweet(me.getState().tweetId, me.getElement(), createOptions(me.getState()))
        .then(function (el) {
            // TODO: callback to java component?
            //console.log("@ev's Tweet has been displayed.")
        });
    }

    me.onStateChange = function() {
        window.twttr.ready(createTweet);
    };

};