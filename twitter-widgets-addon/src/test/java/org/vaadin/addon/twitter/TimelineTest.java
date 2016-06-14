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

import java.util.EnumSet;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Created by marco on 14/06/16.
 */
public class TimelineTest {

    @Test
    public void createTimeline() {
        Timeline timeline = Timeline.profile("user");
        assertThat(timeline.getDatasourcePrimaryArgument()).isEqualTo("user");
        assertThat(timeline.getDatasource()).isEqualTo(Timeline.Datasource.profile);
    }

    @Test
    public void timelineCreationShouldFailWithInvalidArguments() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Timeline.profile(null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Timeline.profile(""))
            .withMessageContaining("must no be empty or blank");
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
