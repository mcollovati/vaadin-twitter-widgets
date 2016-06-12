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
import com.vaadin.shared.communication.SharedState;
import com.vaadin.ui.AbstractJavaScriptComponent;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

/**
 * Base twitter widget that manages common settings.
 *
 * @param <T> the type of the concrete widget
 * @param <S> the type of concrete widget state
 */
@JavaScript("twitter_widgets.js")
public abstract class AbstractWidget<T extends AbstractWidget, S extends BaseWidgetState> extends AbstractJavaScriptComponent {

    private Class<? extends SharedState> stateType;

    protected AbstractWidget() {
        this.stateType = findType();
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        String lang = "en";
        if (locale != null) {
            lang = locale.getLanguage();
        }
        getState().lang = lang;
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
        getState().dnt = doNotTrack;
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
        return getState(false).dnt;
    }

    /**
     * Sets Twitter user mentioned in the default Tweet text as /via @user
     * where appropriate.
     *
     * @param via A valid Twitter screen name
     * @return the object itself for further configuration
     */
    public T withVia(String via) {
        getState().via = via;
        return self();
    }

    /**
     * Returns  user mentioned in the default Tweet text as /via @user.
     *
     * @return a valid Twitter screen name
     */
    public String getVia() {
        return getState(false).via;
    }

    /**
     * Sets Twitter screen names to be suggested for following after a Tweet is posted.
     *
     * @param related Valid Twitter screen names
     * @return the object itself for further configuration
     */
    public T withRelated(String... related) {
        getState().related.addAll(Arrays.asList(related));
        return self();
    }

    /**
     * Removes all Twitter screen names to be suggested for following after a Tweet is posted.
     *
     * @return the object itself for further configuration
     */
    public T withoutRelated() {
        getState().related.clear();
        return self();
    }

    /**
     * Removes Twitter screen names from the list to be suggested for following after a Tweet is posted.
     *
     * @param related screen names to remove from the list
     * @return the object itself for further configuration
     */
    public T removeRelated(String... related) {
        getState().related.removeAll(Arrays.asList(related));
        return self();
    }

    /**
     * Returns the list of Twitter screen names to be suggested for following after a Tweet is posted.
     *
     * @return list of Twitter screen names
     */
    public Set<String> getRelated() {
        return Collections.unmodifiableSet(getState(false).related);
    }

    /**
     * Sets hashtags to be appended to default Tweet text where appropriate.
     *
     * @param hashtag hastags to be appended
     * @return the object itself for further configuration
     */
    public T withHashtag(String... hashtag) {
        getState().hashtags.addAll(Arrays.asList(hashtag));
        return self();
    }

    /**
     * Removes hashtags from the list to be appended to default Tweet text where appropriate.
     *
     * @param hashtag hastag to remove
     * @return the object itself for further configuration
     */
    public T removeHashtag(String... hashtag) {
        getState().hashtags.removeAll(Arrays.asList(hashtag));
        return self();
    }

    /**
     * Removes all hashtag to be appended to default Tweet text where appropriate.
     *
     * @return the object itself for further configuration
     */
    public T withoutHashtags() {
        getState().hashtags.clear();
        return self();
    }

    /**
     * Returns the list of hashtags to be appended to default Tweet text where appropriate.
     *
     * @return list of hashtags
     */
    public Set<String> getHastags() {
        return Collections.unmodifiableSet(getState(false).hashtags);
    }

    // Fluent helpers

    /**
     * Sets the component's caption String.
     *
     * @param caption the new caption <code>String</code> for the component
     * @return the object itself for further configuration
     * @see com.vaadin.ui.AbstractComponent#setCaption(String)
     */
    public T withCaption(String caption) {
        setCaption(caption);
        return self();
    }


    @SuppressWarnings("unchecked")
    protected final T self() {
        return (T) this;
    }

    @Override
    public Class<? extends SharedState> getStateType() {
        return stateType;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected S getState() {
        return (S) super.getState();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected S getState(boolean markAsDirty) {
        return (S) super.getState(markAsDirty);
    }

    @SuppressWarnings("unchecked")
    private Class<S> findType() {
        return (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
