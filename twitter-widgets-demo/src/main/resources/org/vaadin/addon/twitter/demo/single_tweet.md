# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin 7 addon that provides UI components 
to integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).

This demo is powered by [Viritin](https://vaadin.com/directory#!addon/viritin).

## Single Tweet

The [Single Tweet](https://dev.twitter.com/web/embedded-tweets) components brings the content created 
on Twitter into your application.

## Configuration

A Single Tweet may be customized from its default settings with fluent accessors.

See [javadocs](https://vaadindemo-mbf.rhcloud.com/docs/twitter-widgets/api/org/vaadin/addon/twitter/Tweet.html) 
for further information.
 
## Usage

```java
String tweetId = "677563930122821632"; // boost my ego ;)
Tweet tweet = new Tweet(tweetId)
    .withoutCards()
    .withDarkTheme()
    .withHashtag("vaadin", "reactive")
    .withRelated("ligthbend", "DevoxxUK")
    .withVia("marcoc_753");
```

