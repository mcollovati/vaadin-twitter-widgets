/*
 * Copyright (C) 2016-2022 Marco Collovati (mcollovati@gmail.com)
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

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.vaadin.addon.twitter.Timeline;

/**
 * Created by marco on 14/06/16.
 */
@Route(value = "", layout = DemoUI.class)
@RouteAlias("timeline")
public class TimelineDemo extends DemoComponent {

    public TimelineDemo() {
        add(
            createTimeline("Profile Timeline",
                Timeline.profile("vaadin")),
            createTimeline("Likes Timeline",
                Timeline.likes("vaadin")
                    .withBorderColor("green")
                .withChrome(Timeline.Chrome.noheader, Timeline.Chrome.nofooter)
                .withTweetLimit(2)
                .enableDoNotTrack()
                .withHashtag("#vaadin", "#twitter")
            ),
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
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(false);
        verticalLayout.setMargin(false);
        timeline.addClassNames("tw-timeline", "tw-widget");
        timeline.setHeight("400px");

        Label label = new Label(caption);
        label.addClassNames("centered-caption", "font-size-l", "primary-text");
        verticalLayout.add(label, timeline);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.setWidth(null);
        return verticalLayout;
    }

}
