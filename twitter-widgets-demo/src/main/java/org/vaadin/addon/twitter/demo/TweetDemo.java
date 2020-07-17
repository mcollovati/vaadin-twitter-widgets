/*
 * Copyright (C) 2016-2020 Marco Collovati (mcollovati@gmail.com)
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

import java.util.function.Consumer;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.addon.twitter.Tweet;

/**
 * Created by marco on 10/06/16.
 */
@Route(value = "tweet", layout = DemoUI.class)
public class TweetDemo extends DemoComponent {

    private String tweetId = "1015915654053093376";


    public TweetDemo() {
        add(
            createTweet("Default", tweet -> {
            }),
            createTweet("Hidden cards", Tweet::withoutCards),
            createTweet("Hidden conversation", Tweet::withoutConversation),
            createTweet("Dark theme", Tweet::withDarkTheme),
            createTweet("Other options", tweet -> {
                tweet.enableDoNotTrack()
                    .withHashtag("pippo", "pluto")
                    .withRelated("ligthbend", "DevoxxUK")
                    .withVia("marcoc_753");
            })
        );
    }


    private VerticalLayout createTweet(String caption, Consumer<Tweet> customizer) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(false);
        verticalLayout.setMargin(false);
        Tweet tweet = new Tweet(tweetId);
        tweet.addClassNames("tw-widget", "tw-single-tweet");
        customizer.accept(tweet);

        Label label = new Label(caption);
        label.setWidth("100%");
        label.addClassNames("centered-caption", "font-size-l", "primary-text");
        verticalLayout.add(
            label, tweet
        );
        verticalLayout.setAlignItems(Alignment.CENTER); //.expand(tweet);
        verticalLayout.setWidth(null);
        return verticalLayout;
    }

}
