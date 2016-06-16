window.org_vaadin_addon_twitter_demo_HighlightJS = function() {
    var me = this;
    me.onStateChange = function() {
        if (hljs) {
            hljs.highlightBlock(me.getElement(me.getParentId()).querySelector("pre code"));
        }
    };

};