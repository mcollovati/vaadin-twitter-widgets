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

import com.vaadin.flow.templatemodel.TemplateModel;

public interface BaseTwsModel extends TemplateModel {

    String getPrimaryArgument();

    void setPrimaryArgument(String primaryArgument);

    boolean isDoNotTrack();

    void setDoNotTrack(boolean doNotTrack);

    List<String> getHashtags();

    void setHashtags(List<String> hashtags);

    String getLanguage();

    void setLanguage(String lang);

    List<String> getRelated();

    void setRelated(List<String> related);

    String getVia();

    void setVia(String via);

    default void setupDefaults() {
        setLanguage("en");
        setDoNotTrack(false);
        setHashtags(new ArrayList<>());
        setRelated(new ArrayList<>());
    }


}
