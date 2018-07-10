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
import org.vaadin.addon.twitter.Tweet;

public interface TwsTweetModel extends BaseTwsModel {


    Alignment getAlign();

    @Encode(AlignmentEncoder.class)
    void setAlign(Alignment alignment);

    Tweet.Conversation getConversation();

    @Encode(ConversationEncoder.class)
    void setConversation(Tweet.Conversation conversation);

    Tweet.Cards getCards();

    @Encode(CardsEncoder.class)
    void setCards(Tweet.Cards cards);

    String getLinkColor();

    void setLinkColor(String linkColor);

    Tweet.Theme getTheme();

    @Encode(ThemeEncoder.class)
    void setTheme(Tweet.Theme theme);

    default void setupDefaults() {
        BaseTwsModel.super.setupDefaults();
        setConversation(Tweet.Conversation.all);
        setCards(Tweet.Cards.visible);
        setTheme(Tweet.Theme.light);
    }


    public static class AlignmentEncoder extends EnumModelEncoder<Alignment> {
        public AlignmentEncoder() {
            super(Alignment.class);
        }
    }

    public static class ConversationEncoder extends EnumModelEncoder<Tweet.Conversation> {
        public ConversationEncoder() {
            super(Tweet.Conversation.class);
        }
    }

    public static class CardsEncoder extends EnumModelEncoder<Tweet.Cards> {
        public CardsEncoder() {
            super(Tweet.Cards.class);
        }
    }

    public static class ThemeEncoder extends EnumModelEncoder<Tweet.Theme> {
        public ThemeEncoder() {
            super(Tweet.Theme.class);
        }
    }

}
