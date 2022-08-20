# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin addon that provides UI components 
to integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).

## Change attributes at runtime

Widgets support runtime changes of its attributes with the exclusions of the `primary argument` 
(eg `tweet Id`, `user screen name`, etc). 
 
## Usage

```java
Tweet tweet = new Tweet("763309653497446401");

ComboBox<Tweet.Theme> theme = new ComboBox<>();
theme.setItems(Tweet.Theme.values());
theme.setValue(tweet.getTheme());
theme.addValueChangeListener(event -> 
    Optional.ofNullable(event.getValue) 
        .ifPresent(Tweet::withTheme)
);

```