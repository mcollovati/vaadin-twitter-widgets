package org.vaadin.addon.twitter.demo;

import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.twitter.Button;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by marco on 11/06/16.
 */
public class ButtonDemo extends MVerticalLayout {

    public ButtonDemo() {
        withFullWidth().withSpacing(true).withMargin(true);
        addComponents(
            createFollowButtons()
        );
    }

    private Component createFollowButtons() {
        return new MHorizontalLayout(
            new MLabel("Follow Button").withStyleName(ValoTheme.LABEL_COLORED),
            Button.follow("TwitterDev").withCaption("Default"),
            Button.follow("TwitterDev").withCaption("Hide screen name").hideScreenName(),
            Button.follow("TwitterDev").withCaption("Large").large(),
            Button.follow("TwitterDev").withCaption("Without count").withCount(Button.Count.none)
        ).withFullWidth();
    }


}
