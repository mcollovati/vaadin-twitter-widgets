# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin addon that provides UI components 
to integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).

## Follow button

A [mention button](https://dev.twitter.com/web/tweet-button/mention-button) is a special type of 
[Tweet button](https://dev.twitter.com/web/tweet-button) to encourage a new Tweet focused 
on an interaction between the user and a mentioned account.

## Configuration

A mention button may be customized from its default settings with fluent accessors.

See [javadocs](https://mbf-vaadindemo.herokuapp.com/docs/twitter-widgets/api/org/vaadin/addon/twitter/TweetButton.html) 
for further information.

## Usage

```java
TweetButton.mention("vaadin");
TweetButton.mention("vaadin").withText("Take a look!");
TweetButton.mention("vaadin").large();
TweetButton.mention("vaadin").withHashtag("vaadindirectory", "add-on");
TweetButton.mention("vaadin").withVia("marcoc_753");
TweetButton.mention("vaadin").withText("Test some twitter API")
    .withRelated("vaadin", "vaadindirectory");
```