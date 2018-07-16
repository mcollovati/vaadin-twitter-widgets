/*
 * Copyright (C) 2016-2018 Marco Collovati (mcollovati@gmail.com)
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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.vaadin.addon.twitter.TweetButton;

/**
 * Created by marco on 11/06/16.
 */
@Route(value = "button", layout = DemoUI.class)
public class ButtonDemo extends DemoComponent implements HasUrlParameter<String> {

    private static String url = "https://vaadindemo-mbf.rhcloud.com/twitter-widgets/";
    private static final Map<TweetButton.Type, Consumer<HasComponents>> generatorMap = new HashMap<>();

    static {
        generatorMap.put(TweetButton.Type.Share, ButtonDemo::createShareButtons);
        generatorMap.put(TweetButton.Type.Follow, ButtonDemo::createFollowButtons);
        generatorMap.put(TweetButton.Type.Hashtag, ButtonDemo::createHashtagButtons);
        generatorMap.put(TweetButton.Type.Mention, ButtonDemo::createMentionButtons);
    }

    private TweetButton.Type buttonType;

    public ButtonDemo() {
    }


    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        this.removeAll();
        if (parameter != null) {
            this.buttonType = TweetButton.Type.valueOf(parameter);
            generatorMap.get(buttonType).accept(this);
        }
    }

    private static Component makeButtonContainer(TweetButton tweetButton, String caption) {
        Label label = new Label(caption);
        label.setWidth(null);
        label.addClassNames("centered-caption", "font-size-l", "primary-text");
        VerticalLayout div = new VerticalLayout(label,
            tweetButton.withStyleName("tw-widget", "tw-button", "centered-caption")
        );
        div.setAlignItems(FlexComponent.Alignment.CENTER);
        div.setWidth(null);
        div.setMargin(false);
        div.setSpacing(false);
        return div;
    }

    private static void createShareButtons(HasComponents layout) {
        layout.add(
            makeButtonContainer(TweetButton.share(url), "Default"),
            makeButtonContainer(TweetButton.share(url).withText("Share this!"), "Custom text"),
            makeButtonContainer(TweetButton.share(url).large(), "Large"),
            makeButtonContainer(TweetButton.share(url).withHashtag("vaadin", "add-on"), "Hashtags"),
            makeButtonContainer(TweetButton.share(url).withVia("marcoc_753"), "Via"),
            makeButtonContainer(TweetButton.share(url).withText("Test some twitter API")
                .withRelated("twitterapi", "twitter"), "Related")
        );
    }

    private static void createHashtagButtons(HasComponents layout) {
        String hashtag = "vaadin";
        layout.add(
            makeButtonContainer(TweetButton.hashtag(hashtag), "Default"),
            makeButtonContainer(TweetButton.hashtag(hashtag).withText("Share this addon!"), "Custom text"),
            makeButtonContainer(TweetButton.hashtag(hashtag).large(), "Large"),
            makeButtonContainer(TweetButton.hashtag(hashtag).withHashtag("vaadindirectory", "add-on"), "Hashtags"),
            makeButtonContainer(TweetButton.hashtag(hashtag).withVia("marcoc_753"), "Via"),
            makeButtonContainer(TweetButton.hashtag(hashtag).withText("Test some twitter API")
                .withRelated("twitterapi", "twitter"), "Related")
        );
    }

    private static void createMentionButtons(HasComponents layout) {
        String screenName = "marcoc_753";
        layout.add(
            makeButtonContainer(TweetButton.mention(screenName), "Default"),
            makeButtonContainer(TweetButton.mention(screenName).withText("Got your addon!"), "Custom text"),
            makeButtonContainer(TweetButton.mention(screenName).large(), "Large"),
            makeButtonContainer(TweetButton.mention(screenName).withHashtag("vaadindirectory", "add-on"), "Hashtags"),
            makeButtonContainer(TweetButton.mention(screenName).withVia("marcoc_753"), "Via"),
            makeButtonContainer(TweetButton.mention(screenName).withText("Test some twitter API")
                .withRelated("vaadin", "vaadindirectory"), "Related")
        );
    }

    private static void createFollowButtons(HasComponents layout) {
        layout.add(
            makeButtonContainer(TweetButton.follow("TwitterDev"), "Default"),
            makeButtonContainer(TweetButton.follow("TwitterDev").hideScreenName(), "Hide screen name"),
            makeButtonContainer(TweetButton.follow("TwitterDev").large(), "Large"),
            makeButtonContainer(TweetButton.follow("TwitterDev").withCount(TweetButton.Count.none), "Without count")
        );
    }


}
