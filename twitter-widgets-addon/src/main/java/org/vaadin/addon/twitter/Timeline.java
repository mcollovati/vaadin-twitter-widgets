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

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

/**
 * Embed multiple Tweets in a compact, single-column view.
 *
 * Display the latest Tweets from a single Twitter account, multiple accounts, or tap into the worldwide
 * conversation around a topic grouped in a search result.
 *
 * A Twitter collection may be rendered in either a list or a responsive grid template.
 *
 * Documentation is taken from <a href="https://dev.twitter.com/web/overview">Twitter for Websites</a>.
 */
@Tag("tws-timeline")
@HtmlImport("src/tws-timeline.html")
public final class Timeline extends AbstractWidget<Timeline> {


    private Timeline(Datasource datasource, String primaryArgument) {
        getElement().setProperty("datasource", datasource.toString());
        setPrimaryArgument(Objects.requireNonNull(primaryArgument));
        if (primaryArgument.trim().isEmpty()) {
            throw new IllegalArgumentException("primary argument must no be empty or blank");
        }
    }

    /**
     * Creates a timeline with Tweets from an individual user identified
     * by {@code screenName}.
     *
     * @param screenName a valid Twitter username
     * @return a timeline instance
     */
    public static Timeline profile(String screenName) {
        return new Timeline(Datasource.profile, screenName);
    }

    /**
     * Creates a timeline with an individual Twitter user's likes.
     *
     * @param screenName a valid Twitter username
     * @return a timeline instance
     */
    public static Timeline likes(String screenName) {
        return new Timeline(Datasource.likes, screenName);
    }

    /**
     * Creates a timeline with Tweets from a collection.
     *
     * @param collectionId a valid Twitter collection id
     * @return a timeline instance
     */
    public static Timeline collection(String collectionId) {
        return new Timeline(Datasource.collection, collectionId);
    }

    /**
     * Creates a timeline with Twitter content that you have the URL for.
     *
     * Supported content includes profiles, likes, lists, and collections.
     *
     * @param url The permalink to one of a profile, likes timeline, list, or collection
     * @return a timeline instance
     */
    public static Timeline url(String url) {
        try {
            return url(new URL(Objects.requireNonNull(url)));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }

    /**
     * Creates a timeline with Twitter content that you have the URL for.
     *
     * Supported content includes profiles, likes, lists, and collections.
     *
     * @param url The permalink to one of a profile, likes timeline, list, or collection
     * @return a timeline instance
     */
    public static Timeline url(URL url) {
        try {
            return new Timeline(Datasource.url, url.toURI().toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }

    /**
     * Creates a timeline with a timeline widget configuration.
     *
     * @param widgetId A valid Twitter widget id
     * @return a timeline instance
     */
    public static Timeline widget(String widgetId) {
        return new Timeline(Datasource.widget, widgetId);
    }

    /**
     * Creates a timeline with a Twitter list.
     *
     * @param screenName A valid Twitter screen name
     * @param slug       The string identifier for a list
     * @return a timeline instance
     */
    public static Timeline list(String screenName, String slug) {
        if (Objects.requireNonNull(screenName).trim().isEmpty()) {
            throw new IllegalArgumentException("screenName must no be empty or blank");
        }
        if (Objects.requireNonNull(slug).trim().isEmpty()) {
            throw new IllegalArgumentException("slug must no be empty or blank");
        }
        return new Timeline(Datasource.list, String.format("%s@%s", slug, screenName));
    }

    /**
     * Creates a timeline with a Twitter list.
     *
     * @param listId A valid Twitter list id
     * @return a timeline instance
     */
    public static Timeline list(String listId) {
        return new Timeline(Datasource.list, listId);
    }

    /**
     * Returns the datasource primary argument.
     *
     * Depending on {@link Datasource} type could be a screen name, a collection id,
     * an url, a widget id or a combination of screenName and slug (slugId@screenName).
     *
     * @return the datasource primary argument as string.
     */
    public String getDatasourcePrimaryArgument() {
        return internalGetPrimaryArgument();
    }

    /**
     * Returns the datasource type of this timeline.
     *
     * @return the datasource type of this timeline
     */
    public Datasource getDatasource() {
        return Datasource.valueOf(getElement().getProperty("datasource"));
    }

    /**
     * Apply the specified aria-polite behavior to the rendered timeline.
     *
     * @param ariaPolite aria-polite behavior.
     * @return the object itself for further configuration
     */
    public Timeline withAriaPolite(AriaPolite ariaPolite) {
        getElement().setProperty("ariaPolite", ariaPolite.toString());
        return this;
    }

    /**
     * Returns the aria-polite behavior.
     *
     * @return the aria-polite behavior
     */
    public AriaPolite getAriaPolite() {
        return AriaPolite.valueOf(getElement().getProperty("ariaPolite"));
    }

    /**
     * Adjust the color of borders inside the widget.
     *
     * @param borderColor Hexadecimal color value.
     * @return the object itself for further configuration
     */
    public Timeline withBorderColor(String borderColor) {
        getElement().getProperty("borderColor", borderColor);
        return this;
    }

    /**
     * Returns the color of borders.
     *
     * @return color value
     */
    public String getBorderColor() {
        return getElement().getProperty("borderColor");
    }


    /**
     * Set the chrome components that defines the display of design elements in the widget.
     *
     * @param chrome chrome components
     * @return the object itself for further configuration
     */
    public Timeline withChrome(Chrome... chrome) {
        if (chrome.length == 0) {
            throw new IllegalArgumentException("At least one chrome component is required");
        }
        setStringList("chrome", chromeToString(chrome).stream());
        return this;
    }

    /**
     * Removes chrome components from the current configuration.
     *
     * @param chrome chrome components to remove
     * @return the object itself for further configuration
     */
    public Timeline withoutChrome(Chrome... chrome) {
        if (chrome.length == 0) {
            clearStringList("chrome");
        } else {
            removeFromStringList("chrome", chromeToString(chrome).toArray(new String[chrome.length]));
        }
        return this;
    }

    /**
     * Adds chrome components to the the current configuration.
     *
     * @param chrome chrome components to add
     * @return the object itself for further configuration
     */
    public Timeline addChrome(Chrome... chrome) {
        if (chrome.length == 0) {
            throw new IllegalArgumentException("At least one chrome component is required");
        }
        List<String> actual = getStringList("chrome");
        actual.addAll(chromeToString(chrome));
        setStringList("chrome", actual.stream());
        return this;
    }

    /**
     * Returns the chrome components applied to the widget.
     *
     * @return a list of chrome components
     */
    public Set<Chrome> getChrome() {
        LinkedHashSet<Chrome> actual = getStringList("chrome")
            .stream().map(Chrome::valueOf)
            .collect(Collectors.toCollection(LinkedHashSet::new));
        return Collections.unmodifiableSet(actual);
    }


    /**
     * Sets the number of Tweets that must be displayed.
     *
     * {@code tweetLimit} must be between 1 and 20.
     *
     * Note that the height of the widget will be calculated based on specified tweetLimit
     *
     * @param tweetLimit number of Tweets that must be displayed
     * @return the object itself for further configuration
     * @throws IllegalArgumentException if {@code tweetLimit} is less than 1 or greater than 20
     */
    public Timeline withTweetLimit(int tweetLimit) {
        if (tweetLimit < 1 || tweetLimit > 20) {
            throw new IllegalArgumentException("Tweet limit must be between 1 and 20");
        }
        getElement().setProperty("tweetLimit", tweetLimit);
        return this;
    }

    /**
     * Returns the number of tweets that must be displayed.
     *
     * @return the number of tweets that must be displayed
     */
    public int getTweetLimit() {
        return getElement().getProperty("tweetLimit", 0);
    }

    private Set<String> chromeToString(Chrome[] chrome) {
        return Stream.of(chrome).map(Chrome::name).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * The data source definition describes what content will hydrate the embedded timeline.
     */
    public enum Datasource {
        /**
         * Tweets from an individual user.
         *
         * Primary argument must be a valid screen name.
         */
        profile,
        /**
         * Individual Twitter user's likes.
         *
         * Primary argument must be a valid screen name.
         */
        likes,
        /**
         * Tweets from a collection.
         *
         * Primary argument must be a valid collection id.
         */
        collection,
        /**
         * Twitter list.
         *
         * Primary argument must be a valid screen name combined
         * with a list id or slug (string identifier).
         */
        list,
        /**
         * Twitter content identified by URL.
         *
         * Primary argument must be a permalink to one of a profile,
         * likes timeline, list, or collection.
         */
        url,
        /**
         * Use a timeline widget configuration.
         *
         * Primary argument must be a valid widget id.
         */
        widget
    }

    /**
     * Display of design elements in the widget.
     */
    public enum Chrome {
        /**
         * Hides the timeline header.
         *
         * Implementing sites must add their own Twitter attribution, link to the source timeline,
         * and comply with other Twitter
         * <a href="https://about.twitter.com/company/display-requirements">display requirements</a>.
         */
        noheader,
        /**
         * Hides the timeline footer and Tweet composer link, if included in the timeline widget type.
         */
        nofooter,
        /**
         * Removes all borders within the widget including borders surrounding the widget area and separating Tweets.
         */
        noborders,
        /**
         * Removes the widget's background color.
         */
        transparent,
        /**
         * Crops and hides the main timeline scrollbar, if visible.
         *
         * Please consider that hiding standard user interface components can affect
         * the accessibility of your website.
         */
        noscrollbar
    }

    /**
     * Aria-polite behavior.
     */
    public enum AriaPolite {
        /**
         * Polite.
         */
        polite,
        /**
         * Assertive.
         */
        assertive,
        /**
         * Rude.
         */
        rude
    }
}
