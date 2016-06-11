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

@JavaScript("twitter_button.js")
public class TweetButton extends AbstractWidget<TweetButton, TweetButtonState> {


    private TweetButton(String primaryArgument, Type buttonType) {
        getState().primaryArgument = Objects.requireNonNull(primaryArgument);
        getState().buttonType = Objects.requireNonNull(buttonType);
    }

    public static TweetButton follow(String screenName) {
        return new TweetButton(screenName, Type.Follow);
    }
    public static TweetButton hashtag(String hashtag) {
        return new TweetButton(hashtag, Type.Hashtag);
    }
    public static TweetButton mention(String screenName) {
        return new TweetButton(screenName, Type.Mention);
    }
    public static TweetButton share(String url) {
        Objects.requireNonNull(url);
        try {
            return share(new URL(url).toURI());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }
    private static TweetButton share(URI uri) {
        return new TweetButton(uri.toString(), Type.Share);
    }
    public static TweetButton share(URL url) {
        Objects.requireNonNull(url);
        try {
            return share(url.toURI());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }


    public String getPrimaryArgument() {
        return getState(false).primaryArgument;
    }

    public Type getType() {
        return getState(false).buttonType;
    }

    public TweetButton withText(String text) {
        getState().text = text;
        return this;
    }

    public String getText() {
        return getState(false).text;
    }

    public TweetButton withCount(Count count) {
        getState().count = count;
        return this;
    }

    public Count getCount() {
        return getState(false).count;
    }

    public TweetButton withSize(Size size) {
        getState().size = size;
        return this;
    }

    public TweetButton medium() {
        return withSize(Size.medium);
    }

    public TweetButton large() {
        return withSize(Size.large);
    }

    public Size getSize() {
        return getState(false).size;
    }

    public TweetButton withScreenName(boolean visible) {
        getState().showScreenName = visible;
        return this;
    }
    public TweetButton hideScreenName() {
        return withScreenName(false);
    }

    public TweetButton showScreenName() {
        return withScreenName(true);
    }



    public enum Type {
        Share, Follow, Hashtag, Mention
    }


    public enum Count {
        none, horizontal, vertical;
    }

    public enum Size {
        medium, large;
    }
}
