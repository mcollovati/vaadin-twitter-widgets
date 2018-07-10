package org.vaadin.addon.twitter.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("markdown-element")
@HtmlImport("src/markdown-template.html")
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
