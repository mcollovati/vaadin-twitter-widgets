# Twitter widgets for Vaadin

Twitter widgets for Vaadin is a Vaadin 7 addon that provides UI components 
for integrate an application with Twitter using [Twitter for Websites](https://dev.twitter.com/web/overview).
 
Following widgets are implemented

* [Single Tweet](https://dev.twitter.com/web/embedded-tweets)
* [Follow Button](https://dev.twitter.com/web/follow-button)
* [Tweet Button](https://dev.twitter.com/web/tweet-button)
* [Hashtag Button](https://dev.twitter.com/web/tweet-button/hashtag-button)
* [Mention Button](https://dev.twitter.com/web/tweet-button/mention-button)
* [Timeline](https://dev.twitter.com/web/embedded-timelines)

For more information see [Twitter for Websites documentation](https://dev.twitter.com/web/overview).

## Online demo

Try the add-on demo at https://vaadindemo-mbf.rhcloud.com/twitter-widgets/

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, 
go to https://vaadin.com/directory#!addon/twitter-widgets-for-vaadin.

## Building and running demo

```
git clone https://github.com/mcollovati/vaadin-twitter-widgets                                                   
mvn clean install
cd demo
mvn jetty:run
```

To see the demo, navigate to http://localhost:8080/

 
## Release notes

### Version 1.0

- Single Tweet
- Follow Button
- Tweet Button
- Hashtag Button
- Mention Button
- Timeline

## Roadmap

This component is developed as a hobby with no public roadmap or any guarantees of upcoming releases. 

That said, the following features are planned for upcoming releases:

- Better widget loading
- Server side listeners for script events
- [Moment](https://dev.twitter.com/web/embedded-moments)
- [Single Tweet with Video](https://dev.twitter.com/web/embedded-video)
- [Twitter Cards](https://dev.twitter.com/cards/overview)

Investigation on [REST](https://dev.twitter.com/rest/public) 
and [Streaming](https://dev.twitter.com/streaming/overview) APIs are also planned. 

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.


# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

addd a dependency to your pom

```xml   
    <dependency>
        <groupId>org.vaadin.addon</groupId>
        <artifactId>twitter-widgets</artifactId>
        <version>ENTER LATEST VERSION</version>
    </dependency>
```

and use the component in your code

```java

    new CssLayout(
        new Tweet(tweetId).withDarkTheme().withoutCards(),
        TweetButton.share(url).withText("Share this!").large(),
        TweetButton.hashtag("vaadin").withHashtag("vaadindirectory", "add-on"),
        TweetButton.mention("vaadin").withVia("marcoc_753")
            .withText("Test some twitter API")
            .withRelated("twitterapi", "twitter")                    
    );

```

For a more comprehensive example, see src/main/java/org/vaadin/addon/twitter/demo/DemoUI.java in demo project.

## API

Twitter widgets for Vaadin JavaDoc is available online at https://vaadindemo-mbf.rhcloud.com/docs/twitter-widgets/api/