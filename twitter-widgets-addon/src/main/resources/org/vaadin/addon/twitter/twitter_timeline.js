window.org_vaadin_addon_twitter_Timeline = function() {
    var me = this;

    var datasourceGenerators = {
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
    var createDataSource = function(state) {
        var ds = {
            sourceType: state.datasource
        };
        datasourceGenerators[ds.sourceType](ds, state);
        return ds;
    };

    var optsNames = [
        "ariaPolite", "borderColor", "tweetLimit",
        { key: "chrome", map: function(val) { return window.vaadinTwttr.arrayToStringList(val) || undefined; } }
    ];
    var createOptions = function(state) {
        var opts = window.vaadinTwttr.mergeOptions(state, optsNames);
        if (me.getElement().clientHeight > 0) {
            opts.height = me.getElement().clientHeight;
        };
        //console.log("==================== Timeline OPTS", opts);
        return opts;
    };

    var createTimeline = function() {
        var state = me.getState();
        window.twttr.widgets.createTimeline(createDataSource(state), me.getElement(), createOptions(state))
            .then(function (el) {
                // TODO: callback to java component?
            });
    };

    me.onStateChange = function() {
        window.twttr.ready(createTimeline);
    };

};