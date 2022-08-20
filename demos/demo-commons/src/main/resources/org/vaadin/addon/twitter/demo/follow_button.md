# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin addon that provides UI components 
to integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).

## Follow button

The [Follow button](https://dev.twitter.com/web/follow-button) is a small button displayed 
on your websites to help users easily follow a Twitter account.

## Configuration

A follow button may be customized from its default settings with fluent accessors.

See [javadocs](https://mbf-vaadindemo.herokuapp.com/docs/twitter-widgets/api/org/vaadin/addon/twitter/TweetButton.html) 
for further information.
 
## Usage

```java
TweetButton.follow("TwitterDev");
TweetButton.follow("TwitterDev").hideScreenName();
TweetButton.follow("TwitterDev").large();
TweetButton.follow("TwitterDev").withCount(TweetButton.Count.none);
```