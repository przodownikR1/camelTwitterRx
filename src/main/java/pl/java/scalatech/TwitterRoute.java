package pl.java.scalatech;

import java.net.URLEncoder;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TwitterRoute extends RouteBuilder {
    @Value("${twitter.consumerKey}")
    String twitterConsumerKey;
    @Value("${twitter.consumerSecret}")
    String twitterConsumerSecret;
    @Value("${twitter.accessToken}")
    String twitterAccessToken;
    @Value("${twitter.accessTokenSecret}")
    String twitterAccessTokenSecret;

        @Override
        public void configure() throws Exception {
        // @formatter:off
            String twitter = "twitter://streaming/filter?type=event&"
                    +         "consumerKey={{twitter.consumerKey}}&" 
                            + "consumerSecret={{twitter.consumerSecret}}&"               
                            + "accessToken={{twitter.accessToken}}&" 
                            + "accessTokenSecret={{twitter.accessTokenSecret}}&"
                    + "keywords="+ URLEncoder.encode("java", "utf8");
            from(twitter).to("direct:out");
         //  @formatter:on
    }

}
