/*
 * Copyright (C) 2016-2022 Marco Collovati (mcollovati@gmail.com)
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

import java.net.URI;
import java.net.URL;
import java.util.Objects;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;

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
 */
@Tag("tws-button")
@JsModule("./src/tws-button.js")
public final class TweetButton extends AbstractPolymerWidget<TweetButton> {


    private TweetButton(String primaryArgument, Type buttonType) {

        setPrimaryArgument(Objects.requireNonNull(primaryArgument));
        getElement().setProperty("buttonType", Objects.requireNonNull(buttonType).toString());
        withCount(TweetButton.Count.horizontal);
        withSize(TweetButton.Size.medium);
        showScreenName();

        // Empty DIV will be used as container for twitter button
        // Should use SLOT because button is not rendered in Chrome
        // when using shadow DOM
        Element buttonContainer = ElementFactory.createDiv();
        //buttonContainer.setAttribute("slot", "twsContainer");
        getElement().appendChild(buttonContainer);
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
     * Creates a new <a href="https://dev.twitter.com/web/tweet-button">Tweet button</a>
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
     * Creates a new <a href="https://dev.twitter.com/web/tweet-button">Tweet button</a>
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
        return internalGetPrimaryArgument();
    }

    /**
     * Returns the type of button.
     *
     * @return the type of button.
     */
    public Type getType() {
        return Type.valueOf(getElement().getProperty("buttonType"));
    }

    /**
     * Sets the text a user sees in the Tweet Web Intent.
     *
     * @param text text to show in Tweet Web Intent
     * @return the object itself for further configuration
     */
    public TweetButton withText(String text) {
        getElement().setProperty("text", text);
        return this;
    }

    /**
     * Returns the text shown in Tweet Web Intent.
     *
     * @return the text shown in Tweet Web Intent
     */
    public String getText() {
        return getElement().getProperty("text");
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
        getElement().setProperty("count", count.toString());
        return this;
    }

    /**
     * Returns how count should be displayed.
     *
     * @return how count should be displayed
     */
    public Count getCount() {
        return Count.valueOf(getElement().getProperty("count"));
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
        getElement().setProperty("size", size.toString());
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
        return Size.valueOf(getElement().getProperty("size"));
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
        getElement().setProperty("screenName", visible);
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

    public boolean isScreenNameVisible() {
        return getElement().getProperty("screenName", true);
    }

    /**
     * Button type.
     */
    public enum Type {
        /**
         * <a href="https://dev.twitter.com/web/tweet-button">Share</a> button type.
         */
        Share,
        /**
         * <a href="https://dev.twitter.com/web/follow-button">Follow</a> button type.
         */
        Follow,
        /**
         * <a href="https://dev.twitter.com/web/tweet-button/hashtag-button">Hashtag</a> button type.
         */
        Hashtag,
        /**
         * <a href="https://dev.twitter.com/web/tweet-button/mention-button">Mention</a> button type.
         */
        Mention
    }

    /**
     * Count visibility.
     */
    public enum Count {
        /**
         * Hidden count.
         */
        none,
        /**
         * Horizontal count.
         */
        horizontal,
        /**
         * Vertical count.
         */
        vertical;
    }

    /**
     * Button size.
     */
    public enum Size {
        /**
         * Medium size.
         */
        medium,
        /**
         * Large size.
         */
        large;
    }
}
