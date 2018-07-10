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

import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.internal.HasCurrentService;
import com.vaadin.flow.server.VaadinService;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TweetTest {

    @Rule
    public VaadinServiceRule vaadinServiceRule = new VaadinServiceRule();

    @Test
    public void tweetIdMustNotBeNull() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> new Tweet(null));
    }

    @Test
    public void tweetIdMustBePositiveNumeric() {
        assertInvalidTweetId("");
        assertInvalidTweetId("a string");
        assertInvalidTweetId("234x");
        assertInvalidTweetId("-234");

        assertValidTweetId("24");
        assertValidTweetId("511181794914627584");
        assertValidTweetId(Long.toString(Long.MAX_VALUE));
    }

    @Test
    public void nullPropertiesAreNotAllowed() {
        Tweet tweet = new Tweet("1");
        assertNullArgumentNotAllowed(() -> tweet.withAlign(null));
        assertNullArgumentNotAllowed(() -> tweet.withConversation(null));
        assertNullArgumentNotAllowed(() -> tweet.withCards(null));
        assertNullArgumentNotAllowed(() -> tweet.withTheme(null));
    }

    private void assertNullArgumentNotAllowed(ThrowableAssert.ThrowingCallable toCheck) {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(toCheck)
            .withMessageContaining("must not be null");
    }

    private void assertValidTweetId(String tweetId) {
        assertThat(new Tweet(tweetId).getTweetId()).isEqualTo(tweetId);
    }

    private void assertInvalidTweetId(String tweetId) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Tweet(tweetId))
            .withMessageContaining("is not a valid tweet ID");
    }
}
