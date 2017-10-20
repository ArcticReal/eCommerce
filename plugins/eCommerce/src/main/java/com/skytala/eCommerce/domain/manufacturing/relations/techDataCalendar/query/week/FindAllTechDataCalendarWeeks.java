
package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.query.week;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week.TechDataCalendarWeekFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.week.TechDataCalendarWeekMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.week.TechDataCalendarWeek;


public class FindAllTechDataCalendarWeeks extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TechDataCalendarWeek> returnVal = new ArrayList<TechDataCalendarWeek>();
try{
List<GenericValue> results = delegator.findAll("TechDataCalendarWeek", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TechDataCalendarWeekMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TechDataCalendarWeekFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
