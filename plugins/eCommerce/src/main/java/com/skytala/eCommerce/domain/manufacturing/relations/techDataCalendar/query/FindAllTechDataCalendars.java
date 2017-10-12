
package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.TechDataCalendarFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.TechDataCalendarMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;


public class FindAllTechDataCalendars extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TechDataCalendar> returnVal = new ArrayList<TechDataCalendar>();
try{
List<GenericValue> results = delegator.findAll("TechDataCalendar", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TechDataCalendarMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TechDataCalendarFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
