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

import org.junit.Test;

import java.net.URL;
import java.util.EnumSet;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Created by marco on 14/06/16.
 */
public class TimelineTest {

    @Test
    public void createProfileTimeline() {
        Timeline timeline = Timeline.profile("user");
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("user");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.profile);
    }

    @Test
    public void profileTimelineCreationShouldFailWithInvalidArguments() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.profile(null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.profile(""))
            .withMessageContaining("must no be empty or blank");
    }

    @Test
    public void createLikesTimeline() {
        Timeline timeline = Timeline.likes("user");
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("user");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.likes);
    }

    @Test
    public void likesTimelineCreationShouldFailWithInvalidArguments() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.likes(null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.likes(""))
            .withMessageContaining("must no be empty or blank");
    }

    @Test
    public void createCollectionTimeline() {
        Timeline timeline = Timeline.collection("collectionId");
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("collectionId");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.collection);
    }

    @Test
    public void collectionTimelineCreationShouldFailWithInvalidArguments() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.collection(null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.collection(""))
            .withMessageContaining("must no be empty or blank");
    }

    @Test
    public void createUrlTimeline() throws Exception {
        Timeline timeline = Timeline.url("https://twitter.com/twitterdev/likes");
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("https://twitter.com/twitterdev/likes");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.url);
        timeline = Timeline.url(new URL("https://twitter.com/twitterdev/likes"));
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("https://twitter.com/twitterdev/likes");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.url);
    }

    @Test
    public void urlTimelineCreationShouldFailWithInvalidArguments() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.url((String)null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.url((URL)null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.url(""))
            .withMessageContaining("Invalid url");
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.url(new URL("http://")))
            .withMessageContaining("Invalid url");
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.url("http://"))
            .withMessageContaining("Invalid url");
    }

    @Test
    public void createWidgetTimeline() throws Exception {
        Timeline timeline = Timeline.widget("123456");
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("123456");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.widget);
    }

    @Test
    public void widgetTimelineCreationShouldFailWithInvalidArguments() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.widget(null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.widget(""))
            .withMessageContaining("must no be empty or blank");
        // TODO: check long?
    }

    @Test
    public void createListTimeline() throws Exception {
        Timeline timeline = Timeline.list("username", "listname");
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("listname@username");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.list);
        timeline = Timeline.list("12345");
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("12345");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.list);
    }

    @Test
    public void listTimelineCreationShouldFailWithInvalidArguments() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.list(null, null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.list("user", null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.list(null, "list"));

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.list("", "list"))
            .withMessageContaining("must no be empty or blank");
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.list("user", " "))
            .withMessageContaining("must no be empty or blank");


        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.list(null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.list(""))
            .withMessageContaining("must no be empty or blank");
        // TODO: check long?
    }


    @Test
    public void testTweetLimitSettings() {
        Timeline timeline = Timeline.profile("user");
        // Default
        assertThat(timeline.getTweetLimit()).isEqualTo(0);


        IntStream.of(-1,0,21)
            .forEach( limit -> assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> timeline.withTweetLimit(limit))
            .withMessageContaining("must be between 1 and 20"));
        IntStream.rangeClosed(1, 20)
            .forEach( limit -> assertThat(timeline.withTweetLimit(limit).getTweetLimit()).isEqualTo(limit));

        new Random().ints().filter( i -> i < 1 || i > 20).limit(100).
        forEach( limit -> assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> timeline.withTweetLimit(limit))
            .withMessageContaining("must be between 1 and 20"));

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> timeline.withTweetLimit((Integer)null));

    }

    @Test
    public void testChromeSettings() {
        Timeline timeline = Timeline.profile("user");
        assertThat(timeline.getChrome()).isEmpty();

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> timeline.withChrome(null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> timeline.withoutChrome(null));
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> timeline.addChrome(null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> timeline.withChrome())
            .withMessageContaining("At least one chrome component is required");
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> timeline.addChrome())
            .withMessageContaining("At least one chrome component is required");


        Timeline.Chrome[] all = Timeline.Chrome.values();
        assertThat(timeline.withChrome(all).getChrome())
            .hasSize(all.length)
            .containsExactly(all);

        EnumSet<Timeline.Chrome> temp = EnumSet.allOf(Timeline.Chrome.class);
        for (Timeline.Chrome chrome : all) {
            temp.remove(chrome);
            assertThat(timeline.withoutChrome(chrome).getChrome())
                .containsExactlyInAnyOrder(temp.toArray(new Timeline.Chrome[temp.size()]));
        }
        assertThat(timeline.getChrome()).isEmpty();

        temp = EnumSet.noneOf(Timeline.Chrome.class);
        for (Timeline.Chrome chrome : all) {
            temp.add(chrome);
            assertThat(timeline.addChrome(chrome).getChrome())
                .containsExactlyInAnyOrder(temp.toArray(new Timeline.Chrome[temp.size()]));
        }
        assertThat(timeline.getChrome()).hasSize(all.length);

        assertThat(timeline.addChrome(all).getChrome())
            .hasSize(all.length)
            .containsExactlyInAnyOrder(all);

        assertThat(timeline.withoutChrome().getChrome()).isEmpty();

    }
}
