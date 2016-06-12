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

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Created by marco on 11/06/16.
 */
public class TweetButtonTest {

    @Test
    public void primaryArgumentIsMandatoryForButtons() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> TweetButton.follow(null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> TweetButton.share((String) null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> TweetButton.share((URL) null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> TweetButton.hashtag(null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> TweetButton.mention(null));
    }

    @Test
    public void buttonCreation() throws MalformedURLException {
        assertThat(TweetButton.follow("me"))
            .extracting("type", "primaryArgument")
            .contains(TweetButton.Type.Follow, "me");
        assertThat(TweetButton.share(new URL("http://www.google.it")))
            .extracting("type", "primaryArgument")
            .contains(TweetButton.Type.Share, "http://www.google.it");
        assertThat(TweetButton.share("http://www.google.it"))
            .extracting("type", "primaryArgument")
            .contains(TweetButton.Type.Share, "http://www.google.it");
        assertThat(TweetButton.hashtag("vaadin"))
            .extracting("type", "primaryArgument")
            .contains(TweetButton.Type.Hashtag, "vaadin");
        assertThat(TweetButton.mention("marcoc_753"))
            .extracting("type", "primaryArgument")
            .contains(TweetButton.Type.Mention, "marcoc_753");
    }

    @Test
    public void shareButtonShouldHaveValidURL() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> TweetButton.share("ciao"));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> TweetButton.share(""));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> TweetButton.share("http://"));
    }


}
