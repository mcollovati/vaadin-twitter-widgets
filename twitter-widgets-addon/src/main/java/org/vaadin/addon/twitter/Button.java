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

import java.util.Objects;

@JavaScript("twitter_button.js")
public class Button extends AbstractWidget<Button, ButtonState> {


    private Button(String primaryArgument, Type buttonType) {
        getState().primaryArgument = Objects.requireNonNull(primaryArgument);
        getState().buttonType = Objects.requireNonNull(buttonType);
    }

    public static Button follow(String screenName) {
        return new Button(screenName, Type.Follow);
    }

    public String getPrimaryArgument() {
        return getState(false).primaryArgument;
    }

    public Type getType() {
        return getState(false).buttonType;
    }

    public Button withText(String text) {
        getState().text = text;
        return this;
    }

    public String getText() {
        return getState(false).text;
    }

    public Button withCount(Count count) {
        getState().count = count;
        return this;
    }

    public Count getCount() {
        return getState(false).count;
    }

    public Button withSize(Size size) {
        getState().size = size;
        return this;
    }

    public Button medium() {
        return withSize(Size.medium);
    }

    public Button large() {
        return withSize(Size.large);
    }

    public Size getSize() {
        return getState(false).size;
    }

    public Button withScreenName(boolean visible) {
        getState().showScreenName = visible;
        return this;
    }
    public Button hideScreenName() {
        return withScreenName(false);
    }

    public Button showScreenName() {
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
