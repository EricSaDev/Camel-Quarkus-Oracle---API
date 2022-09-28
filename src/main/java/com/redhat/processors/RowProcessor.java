package com.redhat.processors;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import com.redhat.models.People;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

 
public class RowProcessor implements Processor {
 
    public void process(Exchange exchange) throws Exception {
        List<Map<String, Object>> rows = exchange.getIn().getBody(List.class);
        People people = new People();
        for (Map<String, Object> row : rows) { 
            people.setPeople_id(((Number) row.get("people_id")).intValue());
            people.setFirstname((String) row.get("firstname"));
            people.setLastname((String) row.get("lastname"));
            people.setEmail((String) row.get("email"));
            people.setStart_date((Timestamp) row.get("start_date"));
            people.setEnd_date((Timestamp) row.get("end_date"));
        }
        exchange.getMessage().setBody(people);
    }
}
