package org.vaadin.addon.twitter.demo;

import java.util.Optional;
import java.util.function.BiConsumer;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.addon.twitter.Tweet;

@Route(value = "dynamic", layout = DemoUI.class)
public class DynamicDemo extends Composite<HorizontalLayout> {

    private Tweet tweet;

    public DynamicDemo() {
        tweet = new Tweet("763309653497446401");
        tweet.addClassNames("tw-widget", "tw-single-tweet");
        FormLayout optionsForm = new FormLayout();
        buildOptions(optionsForm);
        getContent().add(optionsForm);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, tweet);
        getContent().add(tweet);
        getContent().expand(tweet);
    }

    private void buildOptions(FormLayout optionsForm) {

        ComboBox<Tweet.Theme> theme = new ComboBox<>();
        theme.setRequired(true);
        theme.setPreventInvalidInput(true);
        theme.setAllowCustomValue(false);
        theme.setItems(Tweet.Theme.values());
        theme.setValue(tweet.getTheme());
        theme.addValueChangeListener(event -> applyOnTweet(event, Tweet::withTheme));

        optionsForm.addFormItem(theme, "Theme");

    }

    private <T> void applyOnTweet(HasValue.ValueChangeEvent<T> event, BiConsumer<Tweet, T> action) {
        Optional.ofNullable(event.getValue())
            .ifPresent(v -> action.accept(tweet, v));
    }
}
