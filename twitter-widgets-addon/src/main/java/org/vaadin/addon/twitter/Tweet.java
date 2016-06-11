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
package org.vaadin.addon.twitter;

import com.vaadin.annotations.JavaScript;

import java.util.Objects;

/**
 * Embeds a Single Tweet numerical ID of the desired Tweet.
 *
 */
@JavaScript("twitter_tweet.js")
public class Tweet extends AbstractWidget<Tweet, TweetState> {

    /**
     * Creates a new component to embed a single Tweet.
     *
     * @param tweetId The numerical ID of the desired Tweet
     */
    public Tweet(String tweetId) {
        setTweetId(tweetId);
    }

    /**
     * Returns the ID of the Tweet to embed.
     *
     * @return the ID of the Tweet to embed
     */
    public String getTweetId() {
        return getState(false).primaryArgument;
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
        getState().primaryArgument = tweetId;
    }

    /**
     * Set the alignment of the Tweet relative to its container.
     *
     * @param align The alignment of the tweet
     * @return the object itself for further configuration
     */
    public Tweet withAlign(Alignment align) {
        getState().align = Objects.requireNonNull(align, "align must not be null");
        return this;
    }

    /**
     * Returns the alignment of the Tweet relative to its container.
     *
     * @return the alignment of the Tweet relative to its container
     */
    public Alignment getAlign() {
        return getState(false).align;
    }

    /**
     * Sets if the previous Tweet in the thread will be displayed.
     *
     * Use {@link Conversation#none} to hide the thread and show a Tweet alone.
     * Defaults to {@link Conversation#all}.
     * *
     * @param conversation conversion type
     * @return the object itself for further configuration
     */
    public Tweet withConversation(Conversation conversation) {
        getState().conversation = Objects.requireNonNull(conversation, "conversation must not be null");
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
        return getState(false).conversation;
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
        getState().cards = Objects.requireNonNull(cards, "cards must not be null");
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
        return getState(false).cards;
    }

    /**
     * Adjust the color of links inside the widget.
     *
     * @param linkColor the color to apply to the links
     * @return the object itself for further configuration
     */
    public Tweet withLinkColor(String linkColor) {
        getState().linkColor = linkColor;
        return this;
    }

    /**
     * Returns the color to apply to the links.
     *
     * @return the color to apply to the links
     */
    public String getLinkColor() {
        return getState(false).linkColor;
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
        getState().theme = Objects.requireNonNull(theme, "theme must not be null");
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
        return getState(false).theme;
    }

    /*
    @Override
    protected TweetState getState(boolean markAsDirty) {
        return (TweetState) super.getState(markAsDirty);
    }

    @Override
    protected TweetState getState() {
        return (TweetState) super.getState();
    }
    */

    /**
     * Tweet widgets colorschemes.
     */
    public enum Theme {
        light, dark;
    }

    /**
     * Twitter cards visibility.
     */
    public enum Cards {
        hidden, visible;
    }

    /**
     * Tweet thread visibility.
     */
    public enum Conversation {
        none, all;
    }

}
