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
package org.vaadin.addon.twitter.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.jsoup.safety.Whitelist;
import org.vaadin.addon.twitter.TweetButton;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MPanel;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Theme("demo")
@Title("Twitter widgets Add-on Demo")
@Viewport("width=device-width, user-scalable=no, initial-scale=1.0")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addon.twitter.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }


    private final List<RichText> markdown = Arrays.asList(
        readMarkdown("timeline.md"), readMarkdown("single_tweet.md"), readMarkdown("follow_button.md"),
        readMarkdown("share_button.md"), readMarkdown("hashtag_button.md"), readMarkdown("mention_button.md")
    );

    @Override
    protected void init(VaadinRequest request) {
        Responsive.makeResponsive(this);
        CssLayout info = new CssLayout();
        info.setStyleName("tw-docs");
        info.addComponent(markdown.get(0));

        TabSheet tabSheet = new TabSheet();
        tabSheet.setStyleName("tw-demo-tab");
        tabSheet.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        tabSheet.setSizeFull();
        tabSheet.addTab(new TimelineDemo()).setCaption("Timeline");
        tabSheet.addTab(new TweetDemo()).setCaption("Single Tweet");
        tabSheet.addTab(new ButtonDemo(TweetButton.Type.Follow)).setCaption("Follow Button");
        tabSheet.addTab(new ButtonDemo(TweetButton.Type.Share)).setCaption("Share Button");
        tabSheet.addTab(new ButtonDemo(TweetButton.Type.Hashtag)).setCaption("Hashtag Button");
        tabSheet.addTab(new ButtonDemo(TweetButton.Type.Mention)).setCaption("Mention Button");
        tabSheet.addSelectedTabChangeListener(event -> {
            Component old = info.getComponent(0);
            Component newComp = markdown.get(tabSheet.getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab())));
            info.replaceComponent(old, newComp);
        });
        final MHorizontalLayout layout = new MHorizontalLayout(info, tabSheet)
            .withExpand(info, 4)
            .withExpand(tabSheet, 6)
            .withFullWidth().withFullHeight()
            .withMargin(false).withSpacing(true);
        setContent(new MPanel(layout).withFullWidth().withFullHeight().withStyleName(ValoTheme.PANEL_WELL, "root-container"));

    }

    private static RichText readMarkdown(String markdown) {
        try (Scanner sc = new Scanner(DemoUI.class.getResourceAsStream(markdown))) {
            RichText rt = new RichText()
                .withMarkDown(sc.useDelimiter("\\A").next())
                .setWhitelist(Whitelist.relaxed().addAttributes("code", "class").removeTags("br"));
            HighlightJS.of(rt);
            return rt;
        }
    }

}
