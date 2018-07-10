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

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import org.vaadin.addon.twitter.model.BaseTwsModel;

/**
 * Base twitter widget that manages common settings.
 *
 * @param <T> the type of the concrete widget
 * @param <S> the type of concrete widget state
 */

public abstract class AbstractWidget<T extends AbstractWidget, S extends BaseTwsModel> extends PolymerTemplate<S>
    implements HasSize, HasStyle {

    protected AbstractWidget() {
    }

    /**
     * Enables or disables
     * <a href="https://support.twitter.com/articles/20169453-twitter-supports-do-not-track">Do Not Track</a>
     * for this widget.
     *
     * @param doNotTrack false to disable Do Not Track feature.
     * @return the object itself for further configuration
     */
    public T withDoNotTrack(boolean doNotTrack) {
        getModel().setDoNotTrack(doNotTrack);
        return self();
    }

    /**
     * Enables Do Not Track feature.
     *
     * @return the object itself for further configuration
     * @see #withDoNotTrack(boolean)
     */
    public T enableDoNotTrack() {
        return withDoNotTrack(true);
    }

    /**
     * Disabes Do Not Track feature.
     *
     * @return the object itself for further configuration
     * @see #withDoNotTrack(boolean)
     */
    public T disableDoNotTrack() {
        return withDoNotTrack(false);
    }

    /**
     * Returns the status of Do Not Track feature.
     *
     * @return true if Do Not Track feature is enabled, otherwise false
     * @see #withDoNotTrack(boolean)
     */
    public boolean isDoNotTrack() {
        return getModel().isDoNotTrack();
    }

    /**
     * Sets Twitter user mentioned in the default Tweet text as /via @user
     * where appropriate.
     *
     * @param via A valid Twitter screen name
     * @return the object itself for further configuration
     */
    public T withVia(String via) {
        getModel().setVia(via);
        return self();
    }

    /**
     * Returns  user mentioned in the default Tweet text as /via @user.
     *
     * @return a valid Twitter screen name
     */
    public String getVia() {
        return getModel().getVia();
    }

    /**
     * Sets Twitter screen names to be suggested for following after a Tweet is posted.
     *
     * @param related Valid Twitter screen names
     * @return the object itself for further configuration
     */
    public T withRelated(String... related) {
        getModel().getRelated().addAll(Arrays.asList(related));
        return self();
    }

    /**
     * Removes all Twitter screen names to be suggested for following after a Tweet is posted.
     *
     * @return the object itself for further configuration
     */
    public T withoutRelated() {
        getModel().getRelated().clear();
        return self();
    }

    /**
     * Removes Twitter screen names from the list to be suggested for following after a Tweet is posted.
     *
     * @param related screen names to remove from the list
     * @return the object itself for further configuration
     */
    public T removeRelated(String... related) {
        getModel().getRelated().removeAll(Arrays.asList(related));
        return self();
    }

    /**
     * Returns the list of Twitter screen names to be suggested for following after a Tweet is posted.
     *
     * @return list of Twitter screen names
     */
    public Set<String> getRelated() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(getModel().getRelated()));
    }

    /**
     * Sets hashtags to be appended to default Tweet text where appropriate.
     *
     * @param hashtag hastags to be appended
     * @return the object itself for further configuration
     */
    public T withHashtag(String... hashtag) {
        getModel().getHashtags().addAll(Arrays.asList(hashtag));
        return self();
    }

    /**
     * Removes hashtags from the list to be appended to default Tweet text where appropriate.
     *
     * @param hashtag hastag to remove
     * @return the object itself for further configuration
     */
    public T removeHashtag(String... hashtag) {
        getModel().getHashtags().removeAll(Arrays.asList(hashtag));
        return self();
    }

    /**
     * Removes all hashtag to be appended to default Tweet text where appropriate.
     *
     * @return the object itself for further configuration
     */
    public T withoutHashtags() {
        getModel().getHashtags().clear();
        return self();
    }

    /**
     * Returns the list of hashtags to be appended to default Tweet text where appropriate.
     *
     * @return list of hashtags
     */
    public Set<String> getHastags() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(getModel().getHashtags()));
    }

    // Fluent helpers


    /**
     * Adds one or more style names to this component. Multiple styles can be
     * specified as a space-separated list of style names. The style name will
     * be rendered as a HTML class name, which can be used in a CSS definition.
     *
     * @param style the new style to be added to the component
     * @return the object itself for further configuration
     */
    public T withStyleName(String... style) {
        getElement().getClassList().addAll(Arrays.asList(style));
        return self();
    }


    @SuppressWarnings("unchecked")
    protected final T self() {
        return (T) this;
    }

}
