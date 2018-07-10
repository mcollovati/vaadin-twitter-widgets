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

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.templatemodel.Encode;
import org.vaadin.addon.twitter.Timeline;

public interface TwsTimelineModel extends BaseTwsModel {

    Timeline.Datasource getDatasource();

    @Encode(DatasourceEncoder.class)
    void setDatasource(Timeline.Datasource datasource);

    Timeline.AriaPolite getAriaPolite();

    @Encode(AriaPoliteEncoder.class)
    void setAriaPolite(Timeline.AriaPolite ariaPolite);

    String getBorderColor();

    void setBorderColor(String borderColor);

    List<String> getChrome();

    void setChrome(List<String> chrome);

    int getTweetLimit();

    void setTweetLimit(int tweetLimit);

    default void setupDefaults() {
        BaseTwsModel.super.setupDefaults();
        setAriaPolite(Timeline.AriaPolite.polite);
        setChrome(new ArrayList<>());
    }


    public static class DatasourceEncoder extends EnumModelEncoder<Timeline.Datasource> {
        public DatasourceEncoder() {
            super(Timeline.Datasource.class);
        }
    }

    public static class AriaPoliteEncoder extends EnumModelEncoder<Timeline.AriaPolite> {
        public AriaPoliteEncoder() {
            super(Timeline.AriaPolite.class);
        }
    }

    public static class ChromeEncoder extends EnumModelEncoder<Timeline.Chrome> {
        public ChromeEncoder() {
            super(Timeline.Chrome.class);
        }
    }

}
