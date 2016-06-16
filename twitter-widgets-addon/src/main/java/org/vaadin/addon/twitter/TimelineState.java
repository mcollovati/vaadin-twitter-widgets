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

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Shared state for {@link Timeline} component.
 *
 * @see BaseWidgetState
 * @see com.vaadin.shared.AbstractComponentState
 */
public class TimelineState extends BaseWidgetState {

    public Timeline.Datasource datasource;

    public Timeline.AriaPolite ariaPolite = Timeline.AriaPolite.polite;

    public String borderColor;

    public Set<Timeline.Chrome> chrome = new LinkedHashSet<>();

    public int tweetLimit;

}
