/*
 * Copyright (C) 2016-2022 Marco Collovati (mcollovati@gmail.com)
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
package org.vaadin.addon.twitter.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("markdown-element")
@NpmPackage.Container(value = {
        @NpmPackage(value = "markdown-it", version = "13.0.1"),
        @NpmPackage(value = "markdown-it-highlightjs", version = "4.0.1"),
        @NpmPackage(value = "highlight.js", version = "11.7.0") })
@CssImport(value = "highlight.js/styles/github.css", themeFor = "markdown-element")
@JsModule("./markdown-template.js")
public class Markdown extends Component {

    public Markdown() {
    }

    public void setMarkdown(String markdown) {
        getElement().setProperty("markdown", markdown);
    }

    public String getMarkdown() {
        return getElement().getProperty("markdown");
    }

}
