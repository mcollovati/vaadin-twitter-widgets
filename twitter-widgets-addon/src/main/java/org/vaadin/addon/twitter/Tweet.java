/**
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
import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.ui.AbstractJavaScriptComponent;

import java.util.Objects;

@JavaScript({"twitter_widgets.js", "twitter_tweet.js"})
public class Tweet extends AbstractJavaScriptComponent {


    private boolean tweetId;

    public Tweet(String tweetId) {
        setTweetId(tweetId);
    }

    public String getTweetId() {
        return getState(false).tweetId;
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
        getState().tweetId = tweetId;
    }

    public Tweet withAlign(Align align) {
        getState().align = Objects.requireNonNull(align, "align must not be null");
        return this;
    }

    public Align getAlign() {
        return getState(false).align;
    }

    public Tweet withConversation(Conversation conversation) {
        getState().conversation = Objects.requireNonNull(conversation, "conversation must not be null");
        return this;
    }

    public Tweet withConversation() {
        return withConversation(Conversation.all);
    }

    public Tweet withoutConversation() {
        return withConversation(Conversation.none);
    }

    public Conversation getConversation() {
        return getState(false).conversation;
    }

    public Tweet withCards(Cards cards) {
        getState().cards = Objects.requireNonNull(cards, "cards must not be null");
        return this;
    }

    public Tweet withCards() {
        return withCards(Cards.visible);
    }

    public Tweet withoutCards() {
        return withCards(Cards.hidden);
    }

    public Cards getCards() {
        return getState(false).cards;
    }

    public Tweet withLinkColor(String linkColor) {
        getState().linkColor = linkColor;
        return this;
    }

    public String getLinkColor() {
        return getState(false).linkColor;
    }

    public Tweet withTheme(Theme theme) {
        getState().theme = Objects.requireNonNull(theme, "theme must not be null");
        return this;
    }

    public Tweet withLightTheme() {
        return withTheme(Theme.light);
    }

    public Tweet withDarkTheme() {
        return withTheme(Theme.dark);
    }

    public Theme getTheme() {
        return getState(false).theme;
    }

    @Override
    protected TweetState getState(boolean markAsDirty) {
        return (TweetState) super.getState(markAsDirty);
    }

    @Override
    protected TweetState getState() {
        return (TweetState) super.getState();
    }

    public enum Theme {
        light, dark;
    }

    public enum Cards {
        hidden, visible;
    }

    public enum Conversation {
        none, all;
    }

    public enum Align {
        left, center, right;
    }

}
