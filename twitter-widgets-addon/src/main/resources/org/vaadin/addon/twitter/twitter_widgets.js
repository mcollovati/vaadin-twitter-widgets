window.twttr = (function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0],
    t = window.twttr || {};
  if (d.getElementById(id)) return t;
  js = d.createElement(s);
  js.id = id;
  js.src = "https://platform.twitter.com/widgets.js";
  fjs.parentNode.insertBefore(js, fjs);

  t._e = [];
  t.ready = function(f) {
    t._e.push(f);
  };

  return t;
}(document, "script", "twitter-wjs"));
window.twttr.ready(function() {
    window.vaadinTwttr = function() {
        if (window.vaadinTwttr) return window.vaadinTwttr;
        var t = {};
        var identityFn = function(a) { return a; };
        var arrayToStringList = function(arr) { return (arr || []).join(",")};
        var commonOptsNames = ["dnt", { key: "hashtags", map: arrayToStringList }, "lang",
                               { key: "related", map: arrayToStringList }, "via"];
        var applyOptions = function(state, optsNames, opts) {
            opts = opts || {};
            optsNames.forEach(function(optObj) {
                var key = optObj.key || optObj;
                var mapFn = optObj.map || identityFn;
                if (state.hasOwnProperty(key) && (state[key] === false || state[key])) {
                    opts[key] = mapFn(state[key]);
                }
            });
            return opts;
        };

        t.mergeOptions = function(state, widgetOptions) {
            return applyOptions(state, commonOptsNames.concat(widgetOptions || []));
        };
        return t;
    }();
});