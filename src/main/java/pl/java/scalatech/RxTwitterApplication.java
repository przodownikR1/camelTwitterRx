package pl.java.scalatech;



import org.apache.camel.CamelContext;
import org.apache.camel.Message;
import org.apache.camel.rx.ReactiveCamel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;

@SpringBootApplication
@Slf4j
public class RxTwitterApplication {
	@Autowired
	CamelContext camelContext;

	public static void main(String[] args) {
		SpringApplication.run(RxTwitterApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {

			ReactiveCamel rx = new ReactiveCamel(camelContext);
			rx.Observable<Message> observable = rx.toObservable("direct:out");
			//@formatter:off
			observable.map(t->t.getBody(Status.class)).filter(s-> s.getLang().equals("en") || s.getLang().equals("pl") ).subscribe(status -> {
				log.info("[JAVA ] : createdAt : {}, location: {},lang : {} , user : {}, text : {}",
						status.getCreatedAt(),status.getGeoLocation(),status.getLang(),status.getUser().getEmail(),status.getText());
				log.info("{}",status.getUser().getBiggerProfileImageURLHttps());

			});

			rx.Observable<Message> observableSpring = rx.toObservable("direct:outSpring");
			observableSpring.map(t->t.getBody(Status.class)).filter(s-> s.getLang().equals("en") || s.getLang().equals("pl") ).subscribe(status -> {
				log.info("[Spring] :  createdAt : {}, location: {},lang : {} , user : {}, text : {}",
						status.getCreatedAt(),status.getGeoLocation(),status.getLang(),status.getUser().getEmail(),status.getText());
				log.info("{}",status.getUser().getBiggerProfileImageURLHttps());

			});
			rx.Observable<Message> observableCamel = rx.toObservable("direct:outCamel");
			observableCamel.map(t->t.getBody(Status.class)).filter(s-> s.getLang().equals("en") || s.getLang().equals("pl") ).subscribe(status -> {
				log.info("[Camel] :  createdAt : {}, location: {},lang : {} , user : {}, text : {}",
						status.getCreatedAt(),status.getGeoLocation(),status.getLang(),status.getUser().getEmail(),status.getText());
				log.info("{}",status.getUser().getBiggerProfileImageURLHttps());

			});
			rx.Observable<Message> observableCloud = rx.toObservable("direct:outCloud");
			observableCloud.map(t->t.getBody(Status.class)).filter(s-> s.getLang().equals("en") || s.getLang().equals("pl") ).subscribe(status -> {
				log.info("[Cloud] :  createdAt : {}, location: {},lang : {} , user : {}, text : {}",
						status.getCreatedAt(),status.getGeoLocation(),status.getLang(),status.getUser().getEmail(),status.getText());
				log.info("{}",status.getUser().getBiggerProfileImageURLHttps());

			});
			rx.Observable<Message> observableRX = rx.toObservable("direct:outRx");
				observableRX.map(t->t.getBody(Status.class)).filter(s-> s.getLang().equals("en") || s.getLang().equals("pl") ).subscribe(status -> {
					log.info("[rx] :  createdAt : {}, location: {},lang : {} , user : {}, text : {}",
							status.getCreatedAt(),status.getGeoLocation(),status.getLang(),status.getUser().getEmail(),status.getText());
					log.info("{}",status.getUser().getBiggerProfileImageURLHttps());

				});
			// @formatter:on
		};
	}
}
