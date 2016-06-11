window.org_vaadin_addon_twitter_TweetButton = function() {
    var me = this;
    var optsNames = ["align", "count", "size", "text",
                     { key: "showScreenName", map: function(val) { return (val === false) ? false : true }}];

    var createOptions = function(state) {
        var opts = window.vaadinTwttr.mergeOptions(state, optsNames);
        console.log("=============== OPTS", opts);
        return opts;
    }

    var createButton = function() {
        var state = me.getState();
        var generatorFn = "create" + state.buttonType + "Button";
        window.twttr.widgets[generatorFn](state.primaryArgument, me.getElement(), createOptions(state))
        .then(function (el) {
            // TODO: callback to java component?
            console.log(state.buttonType + " button create with opts")
        });
    }

    me.onStateChange = function() {
        window.twttr.ready(createButton);
    };
};