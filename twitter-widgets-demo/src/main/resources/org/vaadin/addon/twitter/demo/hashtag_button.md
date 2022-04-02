# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin addon that provides UI components 
to integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).

## Hashtag button

A [hashtag button](https://dev.twitter.com/web/tweet-button/hashtag-button) is a special type of 
[Tweet button](https://dev.twitter.com/web/tweet-button) to encourage participation in a conversation grouped by keyword.

## Configuration

A hashtag button may be customized from its default settings with fluent accessors.

See [javadocs](https://mbf-vaadindemo.herokuapp.com/docs/twitter-widgets/api/org/vaadin/addon/twitter/TweetButton.html) 
for further information.

 
## Usage

```java
TweetButton.hashtag("vaadin");
TweetButton.hashtag("vaadin").withText("Share this addon!");
TweetButton.hashtag("vaadin").large();
TweetButton.hashtag("vaadin").withHashtag("vaadindirectory", "add-on");
TweetButton.hashtag("vaadin").withVia("marcoc_753");
TweetButton.hashtag("vaadin").withText("Test some twitter API")
    .withRelated("twitterapi", "twitter");
```