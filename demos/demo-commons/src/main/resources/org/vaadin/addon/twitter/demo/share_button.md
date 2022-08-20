# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin addon that provides UI components 
to integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).

## Share button

The [share button](https://dev.twitter.com/web/tweet-button) is a small button displayed on your website 
to help viewers easily share your content on Twitter.

## Configuration

A share button may be customized from its default settings with fluent accessors.

See [javadocs](https://mbf-vaadindemo.herokuapp.com/docs/twitter-widgets/api/org/vaadin/addon/twitter/TweetButton.html) 
for further information.
 
## Usage

```java
String url = "https://vaadin.com/home";
TweetButton.share(url);
TweetButton.share(url).withText("Share this!");
TweetButton.share(url).large();
TweetButton.share(url).withHashtag("vaadin", "add-on");
TweetButton.share(url).withVia("marcoc_753");
TweetButton.share(url).withText("Test some twitter API")
    .withRelated("twitterapi", "twitter");
```