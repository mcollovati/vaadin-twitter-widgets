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
package org.vaadin.addon.twitter.model;

import com.vaadin.flow.templatemodel.Encode;
import org.vaadin.addon.twitter.Alignment;
import org.vaadin.addon.twitter.TweetButton;

public interface TwsButtonModel extends BaseTwsModel {

    String getText();

    void setText(String text);

    Alignment getAlign();

    @Encode(AlignmentEncoder.class)
    void setAlign(Alignment alignment);

    TweetButton.Count getCount();

    @Encode(CountEncoder.class)
    void setCount(TweetButton.Count count);

    TweetButton.Size getSize();

    @Encode(SizeEncoder.class)
    void setSize(TweetButton.Size size);


    TweetButton.Type getButtonType();

    @Encode(ButtonTypeEncoder.class)
    void setButtonType(TweetButton.Type buttonType);

    boolean isShowScreenName();

    void setShowScreenName(boolean showScreenName);


    default void setupDefaults() {
        BaseTwsModel.super.setupDefaults();
        setCount(TweetButton.Count.horizontal);
        setSize(TweetButton.Size.medium);
        setShowScreenName(true);
    }


    public static class AlignmentEncoder extends EnumModelEncoder<Alignment> {
        public AlignmentEncoder() {
            super(Alignment.class);
        }
    }

    public static class CountEncoder extends EnumModelEncoder<TweetButton.Count> {
        public CountEncoder() {
            super(TweetButton.Count.class);
        }
    }

    public static class SizeEncoder extends EnumModelEncoder<TweetButton.Size> {
        public SizeEncoder() {
            super(TweetButton.Size.class);
        }
    }

    public static class ButtonTypeEncoder extends EnumModelEncoder<TweetButton.Type> {
        public ButtonTypeEncoder() {
            super(TweetButton.Type.class);
        }
    }

}
