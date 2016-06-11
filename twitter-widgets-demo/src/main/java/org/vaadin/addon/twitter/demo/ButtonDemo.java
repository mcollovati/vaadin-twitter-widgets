package org.vaadin.addon.twitter.demo;

import com.google.gwt.thirdparty.guava.common.base.Supplier;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.twitter.TweetButton;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by marco on 11/06/16.
 */
public class ButtonDemo extends MVerticalLayout {

    private String url = "http://vaadindemo-mbf.rhcloud.com/";

    private Map<TweetButton.Type, Consumer<MVerticalLayout>> generatorMap = new HashMap<>();

    public ButtonDemo(TweetButton.Type buttonType) {
        withFullWidth().withSpacing(true).withMargin(true);
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        generatorMap.put(TweetButton.Type.Share, this::createShareButtons);
        generatorMap.put(TweetButton.Type.Follow, this::createFollowButtons);
        generatorMap.put(TweetButton.Type.Hashtag, this::createHashtagButtons);
        generatorMap.put(TweetButton.Type.Mention, this::createMentionButtons);
        generatorMap.get(buttonType).accept(this);
        /*addComponents(
            createFollowButtons(), createShareButtons(), createHashtagButtons(), createMentionButtons()
        );
        */
    }

    private void createShareButtons(MVerticalLayout layout) {
        //return new MVerticalLayout(
        layout.add(
            //new MLabel("Share Button").withStyleName(ValoTheme.LABEL_COLORED).withStyleName("centered-caption"),
            TweetButton.share(url).withCaption("Default"),
            TweetButton.share(url).withCaption("Custom text").withText("Share this!"),
            TweetButton.share(url).withCaption("Large").large(),
            TweetButton.share(url).withCaption("Hashtags").withHashtag("vaadin", "add-on"),
            TweetButton.share(url).withCaption("Via").withVia("marcoc_753"),
            TweetButton.share(url).withCaption("Related").withText("Test some twitter API")
                .withRelated("twitterapi", "twitter")
        );
        //).withFullWidth();
    }

    private void createHashtagButtons(MVerticalLayout layout) {
        String hashtag = "vaadin";
        //return new MVerticalLayout(
        layout.add(
            new MLabel("Hashtag Button").withStyleName(ValoTheme.LABEL_COLORED),
            TweetButton.hashtag(hashtag).withCaption("Default"),
            TweetButton.hashtag(hashtag).withCaption("Custom text").withText("Share this addon!"),
            TweetButton.hashtag(hashtag).withCaption("Large").large(),
            TweetButton.hashtag(hashtag).withCaption("Hashtags").withHashtag("vaadindirectory", "add-on"),
            TweetButton.hashtag(hashtag).withCaption("Via").withVia("marcoc_753"),
            TweetButton.hashtag(hashtag).withCaption("Related").withText("Test some twitter API")
                .withRelated("twitterapi", "twitter")
            //).withFullWidth();
        );
    }

    private void createMentionButtons(MVerticalLayout layout) {
        String screenName = "marcoc_753";
        //return new MVerticalLayout(
        layout.add(
            new MLabel("Mention Button").withStyleName(ValoTheme.LABEL_COLORED),
            TweetButton.mention(screenName).withCaption("Default"),
            TweetButton.mention(screenName).withCaption("Custom text").withText("Got your addon!"),
            TweetButton.mention(screenName).withCaption("Large").large(),
            TweetButton.mention(screenName).withCaption("Hashtags").withHashtag("vaadindirectory", "add-on"),
            TweetButton.mention(screenName).withCaption("Via").withVia("marcoc_753"),
            TweetButton.mention(screenName).withCaption("Related").withText("Test some twitter API")
                .withRelated("vaadin", "vaadindirectory")
            //).withFullWidth();
        );
    }

    private void createFollowButtons(MVerticalLayout layout) {
        //return new MVerticalLayout(
        layout.add(
            new MLabel("Follow Button").withStyleName(ValoTheme.LABEL_COLORED),
            TweetButton.follow("TwitterDev").withCaption("Default"),
            TweetButton.follow("TwitterDev").withCaption("Hide screen name").hideScreenName(),
            TweetButton.follow("TwitterDev").withCaption("Large").large(),
            TweetButton.follow("TwitterDev").withCaption("Without count").withCount(TweetButton.Count.none)
            //).withFullWidth();
        );
    }


}
