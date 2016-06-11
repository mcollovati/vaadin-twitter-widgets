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
package org.vaadin.addon.twitter.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.twitter.TweetButton;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("Twitter widgets Add-on Demo")
@Viewport("width=device-width, user-scalable=no, initial-scale=1.0")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addon.twitter.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        Responsive.makeResponsive(this);

        TabSheet tabSheet = new TabSheet();
        tabSheet.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        //tabSheet.setSizeFull();
        tabSheet.setWidth("100%");
        tabSheet.setSizeFull();
        tabSheet.addTab(new TweetDemo()).setCaption("Single Tweet");
        tabSheet.addTab(new ButtonDemo(TweetButton.Type.Follow)).setCaption("Follow Button");
        tabSheet.addTab(new ButtonDemo(TweetButton.Type.Share)).setCaption("Share Button");
        tabSheet.addTab(new ButtonDemo(TweetButton.Type.Hashtag)).setCaption("Hashtag Button");
        tabSheet.addTab(new ButtonDemo(TweetButton.Type.Mention)).setCaption("Mention Button");


        RichText info = new RichText("Docs");
        info.setStyleName("info");
        info.setWidth("300px");
        final MHorizontalLayout layout = new MHorizontalLayout(info).expand(tabSheet)
            .withFullWidth().withFullHeight()
            .withMargin(true).withSpacing(true);
        setContent(layout);

    }

}
