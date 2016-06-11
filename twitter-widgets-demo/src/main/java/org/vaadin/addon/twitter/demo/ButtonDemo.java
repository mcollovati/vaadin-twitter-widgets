package org.vaadin.addon.twitter.demo;

import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.twitter.TweetButton;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by marco on 11/06/16.
 */
public class ButtonDemo extends MVerticalLayout {

    private String url = "http://vaadindemo-mbf.rhcloud.com/";

    public ButtonDemo() {
        withFullWidth().withSpacing(true).withMargin(true);
        addComponents(
            createFollowButtons(), createShareButtons(), createHashtagButtons(), createMentionButtons()
        );
    }

    private Component createShareButtons() {
        return new MHorizontalLayout(
            new MLabel("Share Button").withStyleName(ValoTheme.LABEL_COLORED),
            TweetButton.share(url).withCaption("Default"),
            TweetButton.share(url).withCaption("Custom text").withText("Share this!"),
            TweetButton.share(url).withCaption("Large").large(),
            TweetButton.share(url).withCaption("Hashtags").withHashtag("vaadin", "add-on"),
            TweetButton.share(url).withCaption("Via").withVia("marcoc_753"),
            TweetButton.share(url).withCaption("Related").withText("Test some twitter API")
                .withRelated("twitterapi", "twitter")
        ).withFullWidth();
    }
    private Component createHashtagButtons() {
        String hashtag = "vaadin";
        return new MHorizontalLayout(
            new MLabel("Hashtag Button").withStyleName(ValoTheme.LABEL_COLORED),
            TweetButton.hashtag(hashtag).withCaption("Default"),
            TweetButton.hashtag(hashtag).withCaption("Custom text").withText("Share this addon!"),
            TweetButton.hashtag(hashtag).withCaption("Large").large(),
            TweetButton.hashtag(hashtag).withCaption("Hashtags").withHashtag("vaadindirectory", "add-on"),
            TweetButton.hashtag(hashtag).withCaption("Via").withVia("marcoc_753"),
            TweetButton.hashtag(hashtag).withCaption("Related").withText("Test some twitter API")
                .withRelated("twitterapi", "twitter")
        ).withFullWidth();
    }

    private Component createMentionButtons() {
        String screenName = "marcoc_753";
        return new MHorizontalLayout(
            new MLabel("Mention Button").withStyleName(ValoTheme.LABEL_COLORED),
            TweetButton.mention(screenName).withCaption("Default"),
            TweetButton.mention(screenName).withCaption("Custom text").withText("Got your addon!"),
            TweetButton.mention(screenName).withCaption("Large").large(),
            TweetButton.mention(screenName).withCaption("Hashtags").withHashtag("vaadindirectory", "add-on"),
            TweetButton.mention(screenName).withCaption("Via").withVia("marcoc_753"),
            TweetButton.mention(screenName).withCaption("Related").withText("Test some twitter API")
                .withRelated("vaadin", "vaadindirectory")
        ).withFullWidth();
    }

    private Component createFollowButtons() {
        return new MHorizontalLayout(
            new MLabel("Follow Button").withStyleName(ValoTheme.LABEL_COLORED),
            TweetButton.follow("TwitterDev").withCaption("Default"),
            TweetButton.follow("TwitterDev").withCaption("Hide screen name").hideScreenName(),
            TweetButton.follow("TwitterDev").withCaption("Large").large(),
            TweetButton.follow("TwitterDev").withCaption("Without count").withCount(TweetButton.Count.none)
        ).withFullWidth();
    }


}
