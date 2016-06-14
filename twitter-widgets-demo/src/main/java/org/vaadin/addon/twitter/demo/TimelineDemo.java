package org.vaadin.addon.twitter.demo;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.twitter.Timeline;
import org.vaadin.addon.twitter.Tweet;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.function.Consumer;

/**
 * Created by marco on 14/06/16.
 */
public class TimelineDemo extends MCssLayout {

    public TimelineDemo() {
        withFullWidth()
            .addComponents(
                createTimeline("Profile Timeline",
                    Timeline.profile("vaadin")
                )
            );
    }

    private VerticalLayout createTimeline(String caption, Timeline timeline) {
        MVerticalLayout verticalLayout = new MVerticalLayout()
            .withSpacing(false).withMargin(false);
        timeline.setStyleName("tw-timeline");
        timeline.setHeight("400px");
        verticalLayout.add(
            new MLabel(caption).withStyleName("centered-caption")
                .withStyleName(ValoTheme.LABEL_LARGE)
                .withStyleName(ValoTheme.LABEL_COLORED),
            timeline
        ).alignAll(Alignment.TOP_CENTER);
        verticalLayout.setWidthUndefined();
        return verticalLayout;
    }

}
