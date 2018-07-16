/*
 * Copyright (C) 2016-2018 Marco Collovati (mcollovati@gmail.com)
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLayout;
import org.vaadin.addon.twitter.TweetButton;

@PageTitle("Twitter widgets Add-on Demo")
@Viewport("width=device-width, user-scalable=no, initial-scale=1.0")
@BodySize(height = "100vh", width = "100%")
@HtmlImport("styles/shared-styles.html")
@SuppressWarnings("serial")
public class DemoUI extends HorizontalLayout implements RouterLayout, BeforeEnterObserver {

    private final List<Markdown> markdown = Arrays.asList(
        readMarkdown("timeline.md"), readMarkdown("single_tweet.md"), readMarkdown("follow_button.md"),
        readMarkdown("share_button.md"), readMarkdown("hashtag_button.md"), readMarkdown("mention_button.md"),
        readMarkdown("dynamic_opts.md")
    );
    private final Div demoArea;
    private final Tabs tabs;
    private final Div info;

    public DemoUI() {
        setSizeFull();
        addClassName("root-container");

        info = new Div(markdown.get(0));
        info.addClassName("tw-docs");

        demoArea = new Div();
        demoArea.setSizeFull();

        tabs = new Tabs();
        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.setWidth("100%");

        tabs.add(new PageTab<>("Timeline", TimelineDemo.class));
        tabs.add(new PageTab<>("Single Tweet", TweetDemo.class));
        tabs.add(new PageTab<>("Follow Button", ButtonDemo.class, TweetButton.Type.Follow.name()));
        tabs.add(new PageTab<>("Share Button", ButtonDemo.class, TweetButton.Type.Share.name()));
        tabs.add(new PageTab<>("Hashtag Button", ButtonDemo.class, TweetButton.Type.Hashtag.name()));
        tabs.add(new PageTab<>("Mention Button", ButtonDemo.class, TweetButton.Type.Mention.name()));
        tabs.add(new PageTab<>("Change Tweet Options", DynamicDemo.class));
        tabs.addSelectedChangeListener(event -> {
            PageTab pageTab = (PageTab) tabs.getSelectedTab();
            getUI().ifPresent(pageTab::navigate);
            updateInfoPanel();
        });
        tabs.setSelectedIndex(0);


        VerticalLayout demoView = new VerticalLayout(
            tabs, demoArea
        );
        add(info, demoView);


        setFlexGrow(4, info);
        setFlexGrow(6, demoView);
    }

    private void updateInfoPanel() {
        info.removeAll();
        info.add(markdown.get(tabs.getSelectedIndex()));
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        if (content != null) {
            demoArea.getElement().appendChild(Objects.requireNonNull(content.getElement()));
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        tabs.getChildren()
            .filter(PageTab.class::isInstance)
            .map(PageTab.class::cast)
            .filter(p -> p.isSelected(event))
            .findFirst()
            .ifPresent( t -> {
                tabs.setSelectedTab(t);
                updateInfoPanel();
            });
    }

    @SuppressWarnings("unchecked")
    private static class PageTab<T extends Component> extends Tab {
        private final Class<T> demoPage;
        private final String parameter;

        public PageTab(String label, Class<T> demoPage) {
            this(label, demoPage, "");
        }

        public PageTab(String label, Class<T> demoPage, String parameter) {
            super(label);
            this.demoPage = demoPage;
            this.parameter = parameter;
        }

        void navigate(UI ui) {
            if (HasUrlParameter.class.isAssignableFrom(demoPage)) {
                ui.navigate((Class)demoPage, parameter);
            } else {
                ui.navigate(demoPage);
            }
        }

        boolean isSelected(BeforeEnterEvent event) {
            String url;
            Router router = event.getSource();
            if (HasUrlParameter.class.isAssignableFrom(demoPage)) {
                url = router.getUrl((Class)demoPage, parameter);
            } else {
                url = router.getUrl(demoPage);
            }
            return event.getLocation().getPath().equals(url);
        }
    }


    /*
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
        final MHorizontalLayout lay out = new MHorizontalLayout(info, tabSheet)
            .withExpand(info, 4)
            .withExpand(tabSheet, 6)
            .withFullWidth().withFullHeight()
            .withMargin(false).withSpacing(true);
        setContent(new MPanel(layout).withFullWidth().withFullHeight().withStyleName(ValoTheme.PANEL_WELL, "root-container"));

    }
*/
    private static Markdown readMarkdown(String markdown) {
        try (Scanner sc = new Scanner(DemoUI.class.getResourceAsStream(markdown))) {
            Markdown rt = new Markdown();
            rt.setMarkdown(sc.useDelimiter("\\A").next());
            return rt;
        }
    }

}
