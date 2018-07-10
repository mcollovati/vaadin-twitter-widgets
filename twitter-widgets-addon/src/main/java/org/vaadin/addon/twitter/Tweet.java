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
package org.vaadin.addon.twitter;

import java.util.Objects;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import org.vaadin.addon.twitter.model.TwsTweetModel;

@Tag("tws-tweet")
@HtmlImport("src/tws-tweet.html")
public class Tweet extends AbstractWidget<Tweet, TwsTweetModel> {

    /**
     * Creates a new component to embed a single Tweet.
     *
     * @param tweetId The numerical ID of the desired Tweet
     */
    public Tweet(String tweetId) {
        getModel().setupDefaults();
        setTweetId(tweetId);
    }

    /**
     * Returns the ID of the Tweet to embed.
     *
     * @return the ID of the Tweet to embed
     */
    public String getTweetId() {
        return getModel().getPrimaryArgument();
    }

    private void setTweetId(String tweetId) {
        Objects.requireNonNull(tweetId);
        try {
            long id = Long.valueOf(tweetId);
            if (id <= 0) {
                throw new IllegalArgumentException(tweetId + " is not a valid tweet ID");
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(tweetId + " is not a valid tweet ID");
        }
        getModel().setPrimaryArgument(tweetId);
    }

    /**
     * Set the alignment of the Tweet relative to its container.
     *
     * @param align The alignment of the tweet
     * @return the object itself for further configuration
     */
    public Tweet withAlign(Alignment align) {
        getModel().setAlign(Objects.requireNonNull(align, "align must not be null"));
        return this;
    }

    /**
     * Returns the alignment of the Tweet relative to its container.
     *
     * @return the alignment of the Tweet relative to its container
     */
    public Alignment getAlign() {
        return getModel().getAlign();
    }

    /**
     * Sets if the previous Tweet in the thread will be displayed.
     *
     * Use {@link Conversation#none} to hide the thread and show a Tweet alone.
     * Defaults to {@link Conversation#all}.
     * *
     *
     * @param conversation conversion type
     * @return the object itself for further configuration
     */
    public Tweet withConversation(Conversation conversation) {
        getModel().setConversation(Objects.requireNonNull(conversation, "conversation must not be null"));
        return this;
    }

    /**
     * Show the previuos Tweet in the thread.
     *
     * @return the object itself for further configuration
     */
    public Tweet withConversation() {
        return withConversation(Conversation.all);
    }

    /**
     * Hides the thread and shows a Tweet alone.
     *
     * @return the object itself for further configuration
     */
    public Tweet withoutConversation() {
        return withConversation(Conversation.none);
    }

    /**
     * Returns the conversation setting.
     *
     * @return the conversation setting
     */
    public Conversation getConversation() {
        return getModel().getConversation();
    }

    /**
     * Toggle whether to render expanded media through Twitter Cards in Tweets.
     *
     * Also applies to images uploaded to Twitter.
     * Defaults to {@link Cards#visible}.
     *
     * @param cards cards visibility
     * @return the object itself for further configuration
     */
    public Tweet withCards(Cards cards) {
        getModel().setCards(Objects.requireNonNull(cards, "cards must not be null"));
        return this;
    }

    /**
     * Render Twitter Cards in Tweets.
     *
     * @return the object itself for further configuration
     */
    public Tweet withCards() {
        return withCards(Cards.visible);
    }

    /**
     * Hide Twitter Cards in Tweets.
     *
     * @return the object itself for further configuration
     */
    public Tweet withoutCards() {
        return withCards(Cards.hidden);
    }

    /**
     * Return cards visibility setting.
     *
     * @return cards visibility setting
     */
    public Cards getCards() {
        return getModel().getCards();
    }

    /**
     * Adjust the color of links inside the widget.
     *
     * @param linkColor the color to apply to the links
     * @return the object itself for further configuration
     */
    public Tweet withLinkColor(String linkColor) {
        getModel().setLinkColor(linkColor);
        return this;
    }

    /**
     * Returns the color to apply to the links.
     *
     * @return the color to apply to the links
     */
    public String getLinkColor() {
        return getModel().getLinkColor();
    }

    /**
     * Set the colorscheme of the widget.
     *
     * Defaults to {@link Theme#light}.
     *
     * @param theme the colorscheme to apply
     * @return the object itself for further configuration
     */
    public Tweet withTheme(Theme theme) {
        getModel().setTheme(Objects.requireNonNull(theme, "theme must not be null"));
        return this;
    }

    /**
     * Applies light colorscheme.
     *
     * @return the object itself for further configuration
     */
    public Tweet withLightTheme() {
        return withTheme(Theme.light);
    }

    /**
     * Applies dark colorscheme.
     *
     * @return the object itself for further configuration
     */
    public Tweet withDarkTheme() {
        return withTheme(Theme.dark);
    }

    /**
     * Returns applied colorscheme.
     *
     * @return applied colorscheme
     */
    public Theme getTheme() {
        return getModel().getTheme();
    }


    /**
     * Tweet widgets colorschemes.
     */
    public enum Theme {
        /**
         * Light theme.
         */
        light,
        /**
         * Dark theme.
         */
        dark;
    }

    /**
     * Twitter cards visibility.
     */
    public enum Cards {
        /**
         * Hide cards.
         */
        hidden,
        /**
         * Show cards.
         */
        visible;
    }

    /**
     * Tweet thread visibility.
     */
    public enum Conversation {
        /**
         * Hide conversation thread.
         */
        none,
        /**
         * Show conversation thread.
         */
        all;
    }

}
