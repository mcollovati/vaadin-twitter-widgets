/*
 * Copyright (C) 2016-2017 Marco Collovati (mcollovati@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.addon.twitter.demo;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.twitter.Timeline;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by marco on 14/06/16.
 */
public class TimelineDemo extends MCssLayout {

    public TimelineDemo() {
        withFullWidth()
            .addComponents(
                createTimeline("Profile Timeline",
                    Timeline.profile("vaadin")),
                createTimeline("Likes Timeline",
                    Timeline.likes("vaadin")),
                createTimeline("Collection Timeline",
                    Timeline.collection("393773266801659904")),
                createTimeline("URL Timeline",
                    Timeline.url("https://twitter.com/twitterdev/likes")),
                createTimeline("Widget Timeline",
                    Timeline.widget("738372609797173249")),
                createTimeline("List Timeline",
                    Timeline.list("vaadin", "vaadin-users"))
        );
    }

    private VerticalLayout createTimeline(String caption, Timeline timeline) {
        MVerticalLayout verticalLayout = new MVerticalLayout()
            .withSpacing(false).withMargin(false);
        timeline.addStyleName("tw-timeline");
        timeline.addStyleName("tw-widget");
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
