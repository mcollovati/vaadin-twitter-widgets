# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin addon that provides UI components 
to integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).

This demo is powered by [Viritin](https://vaadin.com/directory#!addon/viritin).

## Timelines

[Embedded timelines](https://dev.twitter.com/web/embedded-timelines) are an easy way to embed 
multiple Tweets on your application in a compact, single-column view.

See [javadocs](https://mbf-vaadindemo.herokuapp.com/docs/twitter-widgets/api/org/vaadin/addon/twitter/Timeline.html) 
for further information.

### User timeline

A [user timeline](https://dev.twitter.com/web/embedded-timelines/user) displays the latest Tweets ordered 
from newest to oldest from a specific public Twitter account.

```java
Timeline.profile("vaadin");
```

### Likes timeline

A likes timeline displays individual Twitter user's likes.

```java
Timeline.likes("vaadin");
```


### Collection timeline

A [collection timeline](https://dev.twitter.com/web/embedded-timelines/collection) displays multiple Tweets 
curated by a Twitter user in their chosen display order.

```java
Timeline.collection("393773266801659904");
```

### List of users

A [list timeline](https://dev.twitter.com/web/embedded-timelines/list) displays the latest Tweets ordered 
from newest to oldest from a curated public list of Twitter accounts.

```java
Timeline.list("vaadin", "vaadin-users");
```


### Widget timeline

```java
Timeline.widget("738372609797173249");
```

### URL timeline

```java
Timeline.url("https://twitter.com/twitterdev/likes");
```



 