package com.redhat.processors;

import java.util.HashMap;
import java.util.Map;
import com.redhat.models.PeopleInsert;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MapProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        // take the Employee object from the exchange and create the parameter map
        PeopleInsert people = exchange.getIn().getBody(PeopleInsert.class);
        Map<String, Object> PeopleMap = new HashMap<String, Object>();
        PeopleMap.put("firstname", people.getFirstname());
        PeopleMap.put("lastname", people.getLastname());
        PeopleMap.put("email", people.getEmail());
        PeopleMap.put("start_date", people.getStart_date());
        PeopleMap.put("end_date", people.getEnd_date());        
        exchange.getIn().setBody(PeopleMap);
    }
}
