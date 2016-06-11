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
 * Created by marco on 11/06/16.
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

    public T withDoNotTrack(boolean doNotTrack) {
        getState().dnt = doNotTrack;
        return self();
    }

    public T enableDoNotTrack() {
        return withDoNotTrack(true);
    }

    public T disableDoNotTrack() {
        return withDoNotTrack(false);
    }

    public boolean isDoNotTrack() {
        return getState(false).dnt;
    }

    public T withVia(String via) {
        getState().via = via;
        return self();
    }

    public String getVia() {
        return getState(false).via;
    }

    public T withRelated(String... related) {
        getState().related.addAll(Arrays.asList(related));
        return self();
    }

    public T withoutRelated() {
        getState().related.clear();
        return self();
    }

    public T removeRelated(String... related) {
        getState().related.removeAll(Arrays.asList(related));
        return self();
    }

    public Set<String> getRelated() {
        return Collections.unmodifiableSet(getState(false).related);
    }

    public T withHashtag(String... hashtag) {
        getState().hashtags.addAll(Arrays.asList(hashtag));
        return self();
    }

    public T removeHashtag(String... hashtag) {
        getState().hashtags.removeAll(Arrays.asList(hashtag));
        return self();
    }

    public T withoutHashtags() {
        getState().hashtags.clear();
        return self();
    }

    public Set<String> getHastags() {
        return Collections.unmodifiableSet(getState(false).hashtags);
    }

    // Fluent heplers
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
