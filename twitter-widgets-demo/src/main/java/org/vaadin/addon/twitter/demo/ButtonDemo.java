/*
 * Copyright (C) 2016 Marco Collovati (mcollovati@gmail.com)
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
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.twitter.TweetButton;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by marco on 11/06/16.
 */
public class ButtonDemo extends MCssLayout {

    private String url = "http://vaadindemo-mbf.rhcloud.com/";

    private Map<TweetButton.Type, Consumer<MCssLayout>> generatorMap = new HashMap<>();

    public ButtonDemo(TweetButton.Type buttonType) {
        withFullWidth(); //.withSpacing(true).withMargin(true);
        //setDefaultComponentAlignment(Alignment.TOP_CENTER);
        generatorMap.put(TweetButton.Type.Share, this::createShareButtons);
        generatorMap.put(TweetButton.Type.Follow, this::createFollowButtons);
        generatorMap.put(TweetButton.Type.Hashtag, this::createHashtagButtons);
        generatorMap.put(TweetButton.Type.Mention, this::createMentionButtons);
        generatorMap.get(buttonType).accept(this);
    }


    private Component makeButtonContainer(TweetButton tweetButton, String caption) {
        return new MVerticalLayout(
            new MLabel(caption).withWidth("-1")
                .withStyleName("centered-caption")
                .withStyleName(ValoTheme.LABEL_LARGE)
                .withStyleName(ValoTheme.LABEL_COLORED),
            tweetButton.withStyleName("tw-widget", "tw-button", "centered-caption")
        ).withSpacing(false).withMargin(false)
            .alignAll(Alignment.TOP_CENTER)
            .withWidth("-1");
    }

    private void createShareButtons(MCssLayout layout) {
        layout.addComponents(
            makeButtonContainer(TweetButton.share(url), "Default"),
            makeButtonContainer(TweetButton.share(url).withText("Share this!"), "Custom text"),
            makeButtonContainer(TweetButton.share(url).large(), "Large"),
            makeButtonContainer(TweetButton.share(url).withHashtag("vaadin", "add-on"), "Hashtags"),
            makeButtonContainer(TweetButton.share(url).withVia("marcoc_753"), "Via"),
            makeButtonContainer(TweetButton.share(url).withText("Test some twitter API")
                .withRelated("twitterapi", "twitter"), "Related")
        );
    }

    private void createHashtagButtons(MCssLayout layout) {
        String hashtag = "vaadin";
        layout.addComponents(
            makeButtonContainer(TweetButton.hashtag(hashtag), "Default"),
            makeButtonContainer(TweetButton.hashtag(hashtag).withText("Share this addon!"), "Custom text"),
            makeButtonContainer(TweetButton.hashtag(hashtag).large(), "Large"),
            makeButtonContainer(TweetButton.hashtag(hashtag).withHashtag("vaadindirectory", "add-on"), "Hashtags"),
            makeButtonContainer(TweetButton.hashtag(hashtag).withVia("marcoc_753"), "Via"),
            makeButtonContainer(TweetButton.hashtag(hashtag).withText("Test some twitter API")
                .withRelated("twitterapi", "twitter"), "Related")
        );
    }

    private void createMentionButtons(MCssLayout layout) {
        String screenName = "marcoc_753";
        layout.addComponents(
            makeButtonContainer(TweetButton.mention(screenName), "Default"),
            makeButtonContainer(TweetButton.mention(screenName).withText("Got your addon!"), "Custom text"),
            makeButtonContainer(TweetButton.mention(screenName).large(), "Large"),
            makeButtonContainer(TweetButton.mention(screenName).withHashtag("vaadindirectory", "add-on"), "Hashtags"),
            makeButtonContainer(TweetButton.mention(screenName).withVia("marcoc_753"), "Via"),
            makeButtonContainer(TweetButton.mention(screenName).withText("Test some twitter API")
                .withRelated("vaadin", "vaadindirectory"), "Related")
        );
    }

    private void createFollowButtons(MCssLayout layout) {
        layout.addComponents(
            makeButtonContainer(TweetButton.follow("TwitterDev"), "Default"),
            makeButtonContainer(TweetButton.follow("TwitterDev").hideScreenName(), "Hide screen name"),
            makeButtonContainer(TweetButton.follow("TwitterDev").large(), "Large"),
            makeButtonContainer(TweetButton.follow("TwitterDev").withCount(TweetButton.Count.none), "Without count")
        );
    }


}
