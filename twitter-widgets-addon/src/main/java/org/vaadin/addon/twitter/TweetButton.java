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

import java.net.URI;
import java.net.URL;
import java.util.Objects;

/**
 * The Tweet button is a small button displayed on your website to help viewers
 * easily share your content on Twitter.
 *
 * There are four type of Tweet buttons available:
 *
 * <ul>
 * <li>Share button: easily share your content on Twitter</li>
 * <li>Hashtag button: a special type of Tweet button to encourage participation
 * in a conversation grouped by keyword</li>
 * <li>Mention button:  a special type of Tweet button to encourage a new Tweet
 * focused on an interaction between the user and a mentioned account.</li>
 * <li>Follow button: a small button displayed on your websites
 * to help users easily follow a Twitter account</li>
 * </ul>
 *
 * Buttons can be built using static factory methods
 * {@link TweetButton#share(String)}, {@link TweetButton#share(URL)}
 * {@link TweetButton#hashtag(String)}, {@link TweetButton#mention(String)} and
 * {@link TweetButton#follow(String)}.
 *
 */
@JavaScript("twitter_button.js")
public class TweetButton extends AbstractWidget<TweetButton, TweetButtonState> {


    private TweetButton(String primaryArgument, Type buttonType) {
        getState().primaryArgument = Objects.requireNonNull(primaryArgument);
        getState().buttonType = Objects.requireNonNull(buttonType);
    }

    /**
     * Creates a new <a href="https://dev.twitter.com/web/follow-button">Follow button</a>
     * for the given user screen name.
     *
     * @param screenName The Twitter username to be followed.
     * @return a follow button instance
     */
    public static TweetButton follow(String screenName) {
        return new TweetButton(screenName, Type.Follow);
    }

    /**
     * Creates a new <a href="https://dev.twitter.com/web/tweet-button/hashtag-button">Hashtag button</a>
     * for the given hashtag.
     *
     * @param hashtag Hashtag to be Tweeted and displayed on the button
     * @return an hashtag button instance
     */
    public static TweetButton hashtag(String hashtag) {
        return new TweetButton(hashtag, Type.Hashtag);
    }

    /**
     * Creates a new <a href="https://dev.twitter.com/web/tweet-button/mention-button">Mention button</a>
     * for the given user screen name.
     *
     * @param screenName The Twitter username to be mentioned.
     * @return a mention button instance
     */
    public static TweetButton mention(String screenName) {
        return new TweetButton(screenName, Type.Mention);
    }

    /**
     * Creates a new <a href="https://dev.twitter.com/web/follow-button">Tweet button</a>
     * for the given url.
     *
     * @param url The url to be shared
     * @return a tweet button instance
     */
    public static TweetButton share(String url) {
        Objects.requireNonNull(url);
        try {
            return share(new URL(url).toURI());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }

    /**
     * Creates a new <a href="https://dev.twitter.com/web/follow-button">Tweet button</a>
     * for the given url.
     *
     * @param url The url to be shared
     * @return a tweet button instance
     */
    public static TweetButton share(URL url) {
        Objects.requireNonNull(url);
        try {
            return share(url.toURI());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }

    private static TweetButton share(URI uri) {
        return new TweetButton(uri.toString(), Type.Share);
    }


    /**
     * Returns the primary argument for this button.
     *
     * Depending on button type the primary argument could be:
     *
     * <ul>
     * <li>The URL to be shared</li>
     * <li>The screen_name of a user to be followed, or mentioned.</li>
     * <li>Hashtag to be Tweeted and displayed on the button.</li>
     * </ul>
     *
     * @return the primary argument for this button instance.
     */
    public String getPrimaryArgument() {
        return getState(false).primaryArgument;
    }

    /**
     * Returns the type of button.
     *
     * @return the type of button.
     */
    public Type getType() {
        return getState(false).buttonType;
    }

    /**
     * Sets the text a user sees in the Tweet Web Intent.
     *
     * @param text text to show in Tweet Web Intent
     * @return the object itself for further configuration
     */
    public TweetButton withText(String text) {
        getState().text = text;
        return this;
    }

    /**
     * Returns the text shown in Tweet Web Intent.
     *
     * @return the text shown in Tweet Web Intent
     */
    public String getText() {
        return getState(false).text;
    }

    /**
     * Sets how count should be displayed.
     *
     * Share button and Follow button only; vertical count only available for share buttons.
     * Defaults to {@link Count#horizontal}.
     *
     * @param count how count should be displayed
     * @return the object itself for further configuration
     */
    public TweetButton withCount(Count count) {
        getState().count = count;
        return this;
    }

    /**
     * Returns how count should be displayed.
     *
     * @return how count should be displayed
     */
    public Count getCount() {
        return getState(false).count;
    }

    /**
     * Sets the size of the button.
     *
     * Defaults to {@link Size#medium}.
     *
     * @param size the size of the button
     * @return the object itself for further configuration
     */
    public TweetButton withSize(Size size) {
        getState().size = size;
        return this;
    }

    /**
     * Sets the button size to medium.
     *
     * @return the object itself for further configuration
     */
    public TweetButton medium() {
        return withSize(Size.medium);
    }

    /**
     * Sets the button size to large.
     *
     * @return the object itself for further configuration
     */
    public TweetButton large() {
        return withSize(Size.large);
    }

    /**
     * Returns the button size.
     *
     * @return the button size;
     */
    public Size getSize() {
        return getState(false).size;
    }

    /**
     * Sets if screen name should be visible on button.
     *
     * Defaults to true.
     *
     * @param visible true if screen name should be visibile.
     * @return the object itself for further configuration
     */
    public TweetButton withScreenName(boolean visible) {
        getState().showScreenName = visible;
        return this;
    }

    /**
     * Hides screen name on button.
     *
     * @return the object itself for further configuration
     */
    public TweetButton hideScreenName() {
        return withScreenName(false);
    }

    /**
     * Shows screen name on button.
     *
     * @return the object itself for further configuration
     */
    public TweetButton showScreenName() {
        return withScreenName(true);
    }


    /**
     * Button type.
     */
    public enum Type {
        Share, Follow, Hashtag, Mention
    }

    /**
     * Count visibility.
     */
    public enum Count {
        none, horizontal, vertical;
    }

    /**
     * Button size.
     */
    public enum Size {
        medium, large;
    }
}
