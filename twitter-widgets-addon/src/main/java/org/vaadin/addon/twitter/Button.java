package org.vaadin.addon.twitter;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({"twitter_widgets.js","twitter_button.js"})
public class Button extends AbstractJavaScriptComponent  {

    public enum Type {

    }

    @Override
    protected ButtonState getState() {
        return (ButtonState)super.getState();
    }

    @Override
    protected ButtonState getState(boolean markAsDirty) {
        return (ButtonState)super.getState(markAsDirty);
    }
}
