window.org_vaadin_addon_twitter_Tweet = function() {
    var me = this;
    var optsNames = ["align", "conversation", "cards", "linkColor", "theme"];

    var createOptions = function(state) {
        var opts = { width: "auto" };
        optsNames.forEach(function(key) {
            if (state.hasOwnProperty(key) && state[key]) {
                opts[key] = state[key];
            }
        });
        console.log("=============== OPTS", opts);
        return opts;
    }

    var createTweet = function(state) {
        window.twttr.widgets.createTweet(me.getState().tweetId, me.getElement(), createOptions(me.getState()))
        .then(function (el) {
            console.log("@ev's Tweet has been displayed.")
        });
    }

    me.onStateChange = function() {
        window.twttr.ready(createTweet);
    };

};