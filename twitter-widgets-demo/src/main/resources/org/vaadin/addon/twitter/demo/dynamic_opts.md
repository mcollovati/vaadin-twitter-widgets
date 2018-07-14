# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin addon that provides UI components 
to integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).

## Change attributes at runtime

Widgets support runtime changes of its attributes with the exclusions of the `primary argument` 
(eg `tweet Id`, `user screen name`, etc). 
 
## Usage

```java
TweetButton.follow("TwitterDev");
TweetButton.follow("TwitterDev").hideScreenName();
TweetButton.follow("TwitterDev").large();
TweetButton.follow("TwitterDev").withCount(TweetButton.Count.none);
```