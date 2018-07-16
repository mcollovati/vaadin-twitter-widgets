package org.vaadin.addon.twitter.demo;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.BiConsumer;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.addon.twitter.Alignment;
import org.vaadin.addon.twitter.Tweet;

@Route(value = "dynamic", layout = DemoUI.class)
public class DynamicDemo extends Composite<HorizontalLayout> {

    private Tweet tweet;

    public DynamicDemo() {
        tweet = new Tweet("763309653497446401");
        tweet.addClassNames("tw-widget", "tw-single-tweet");
        FormLayout optionsForm = new FormLayout();
        optionsForm.setSizeUndefined();

        buildOptions(optionsForm);
        getContent().add(optionsForm);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, tweet);
        getContent().add(tweet);
        getContent().setFlexGrow(0, optionsForm);
        getContent().setFlexGrow(1, tweet);
    }

    private void buildOptions(FormLayout optionsForm) {

        ComboBox<Alignment> alignment = buildCombobox(tweet.getAlign(), Tweet::withAlign);
        optionsForm.addFormItem(alignment, "Alignment");

        ComboBox<Tweet.Theme> theme = buildCombobox(tweet.getTheme(), Tweet::withTheme);
        optionsForm.addFormItem(theme, "Theme");

        ComboBox<Tweet.Cards> cards = buildCombobox(tweet.getCards(), Tweet::withCards);
        optionsForm.addFormItem(cards, "Cards");

        ComboBox<Tweet.Conversation> conversation = buildCombobox(tweet.getConversation(), Tweet::withConversation);
        optionsForm.addFormItem(conversation, "Conversation");

        TextField linkColor = new TextField();
        linkColor.setPlaceholder("Color in HTML format (#FF00FF)");
        linkColor.addValueChangeListener(event -> tweet.withLinkColor(event.getValue()));
        optionsForm.addFormItem(linkColor, "Link color");

    }

    @SuppressWarnings("unchecked")
    private <T extends Enum<T>> ComboBox<T> buildCombobox(T defValue, BiConsumer<Tweet, T> setter) {

        ComboBox<T> comboBox = new ComboBox<>();
        comboBox.setRequired(true);
        comboBox.setPreventInvalidInput(true);
        comboBox.setAllowCustomValue(false);
        comboBox.setItems(EnumSet.<T>allOf((Class<T>)defValue.getClass()));
        comboBox.setValue(defValue);
        comboBox.addValueChangeListener(event -> applyOnTweet(event, setter));
        return comboBox;
    }

    private <T> void applyOnTweet(HasValue.ValueChangeEvent<T> event, BiConsumer<Tweet, T> action) {
        Optional.ofNullable(event.getValue())
            .ifPresent(v -> action.accept(tweet, v));
    }
}
