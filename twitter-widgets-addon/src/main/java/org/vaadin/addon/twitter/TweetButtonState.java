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

/**
 * Shared state for {@link TweetButton}.
 *
 * @see BaseWidgetState
 * @see com.vaadin.shared.AbstractComponentState
 */
public class TweetButtonState extends BaseWidgetState {

    public String text;

    public Alignment align;

    public TweetButton.Count count = TweetButton.Count.horizontal;

    public TweetButton.Size size = TweetButton.Size.medium;

    public TweetButton.Type buttonType;

    public boolean showScreenName = true;
}
