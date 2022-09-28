package com.redhat.routeBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import com.redhat.models.PeopleInsert;
import com.redhat.processors.MapProcessor;
import com.redhat.processors.RowProcessor;
import com.redhat.processors.RowsProcessor;



public class ApiRouteBuilder extends RouteBuilder{
    protected String LIFE_SPAN_TIME = "{{quarkus.openshift.env.vars.life-span-time}}";

    @Override
    public void configure() throws Exception {        

        //REST and Open API configuration
        restConfiguration().bindingMode(RestBindingMode.json)
        	.component("platform-http")
			.dataFormatProperty("prettyPrint", "true")
			.contextPath("/").port(9000)
			.apiContextPath("/openapi")
			.apiProperty("api.title", "Camel Quarkus Oracle API Demo")
			.apiProperty("api.version", "1.0.0-SNAPSHOT")
            .apiProperty("cors", "true");
        
        //REST methods configuration
        rest().tag("API Demo using Camel and Quarkus with Oracle Database")
        .produces("application/json")
       
        .post("/people")
                .consumes("application/json") 
                .type(PeopleInsert.class)
				.description("Send People to Oracle")
				.route().routeId("postPeolpeSendOracle")
                .convertBodyTo(PeopleInsert.class)
                .to("direct:sendToOracle")                   
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))                     
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))                    
                .endRest()
        .get("/people/{people_id}")
                .description("Gets a People from Oracle from Id")
                .route().routeId("getPeopleFromOracle")
                .log("Called findById API")
                .to("direct:getFromOracleDbById")                
                .endRest()
        .get("/getAll")
                .description("Gets all People from Oracle")
                .route().routeId("getAllPeopleFromOracle")
                .log("Called getAll API")
                .to("direct:getAllFromOracleDb")                
                .endRest()
        ;        
        //Route that sends message to Oracle Database
        from("direct:sendToOracle").routeId("sendToOracle")      
            .process(new MapProcessor())
            .log("Message body: ${body}")
            .to("sql:INSERT INTO people (firstname, lastname, email, start_date, end_date) values (:#firstname, :#lastname, :#email, To_date(:#start_date, 'YYYY-MM-DD'), To_date(:#end_date, 'YYYY-MM-DD'))")
        .setBody(simple("Sucessifully Inserted"))
        ;

        //Route gets object from Oracle by Id
        from("direct:getFromOracleDbById").routeId("getFromOracleDbById")        
            .log("Id searched: ${header.people_id}")
            .to("sql:SELECT * FROM people WHERE people_id = :#people_id AND rownum = 1")
            .log("Response Body: ${body}")
            .process(new RowProcessor())
            .setHeader("LifeSpanTimeSeconds",simple(LIFE_SPAN_TIME))//header for cache use
        .setBody(simple("${body}"))
        ;

        //Route gets all objects from Oracle
        from("direct:getAllFromOracleDb").routeId("getAllFromOracleDb")        
            .to("sql:SELECT * FROM people")
            .log("Response Body: ${body}")
            .process(new RowsProcessor())
        .setBody(simple("${body}"))
        ;
    }
}