package org.vaadin.addon.twitter.demo;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.UI;

/**
 * Created by marco on 15/06/16.
 */
@JavaScript({
    "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.4.0/highlight.min.js",
    "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.4.0/languages/java.min.js",
    "highlightjs_connector.js"
})
@StyleSheet({
    "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.4.0/styles/github.min.css"
})
public class HighlightJS extends AbstractJavaScriptExtension {

    private HighlightJS(AbstractClientConnector component) {
        super(component);
    }

    public static HighlightJS of(AbstractClientConnector component) {
        return component.getExtensions().stream().filter(HighlightJS.class::isInstance)
            .findFirst().map(HighlightJS.class::cast)
            .orElseGet(() -> new HighlightJS(component));
    }
}
