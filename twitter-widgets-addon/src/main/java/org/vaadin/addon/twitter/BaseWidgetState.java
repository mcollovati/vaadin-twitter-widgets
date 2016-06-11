/**
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

import com.vaadin.shared.ui.JavaScriptComponentState;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Common options for all widgets;
 */
public abstract class BaseWidgetState extends JavaScriptComponentState {

    public boolean dnt = false;

    public Set<String> hashtags = new LinkedHashSet<>();

    public String lang = "en";

    public Set<String> related = new LinkedHashSet<>();

    public String via;

}
