package org.vaadin.addon.twitter.demo;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.twitter.Tweet;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.function.Consumer;

/**
 * Created by marco on 10/06/16.
 */
public class TweetDemo extends MCssLayout {

    private String tweetId = "677563930122821632";


    public TweetDemo() {
        withFullWidth();
        //setSpacing(true);
        //setMargin(false);
        addComponents(
            createTweet("Default", tweet -> {}),
            createTweet("Hidden cards", Tweet::withoutCards),
            createTweet("Hidden conversation", Tweet::withoutConversation),
            createTweet("Dark theme", Tweet::withDarkTheme),
            createTweet("Other options", tweet -> {
                tweet.enableDoNotTrack();
                tweet.withHashtag("pippo", "pluto");
                tweet.withRelated("ligthbend", "DevoxxUK");
                tweet.withVia("marcoc_753");
            })
        );
    }

    private VerticalLayout createTweet(String caption, Consumer<Tweet> customizer) {
        MVerticalLayout verticalLayout = new MVerticalLayout()
            .withFullWidth().withSpacing(true).withMargin(false);
        Tweet tweet = new Tweet(tweetId);
        customizer.accept(tweet);
        verticalLayout.add(
            new MLabel(caption).withStyleName(ValoTheme.LABEL_LARGE)
        ).expand(tweet);
        return verticalLayout;
    }

}
