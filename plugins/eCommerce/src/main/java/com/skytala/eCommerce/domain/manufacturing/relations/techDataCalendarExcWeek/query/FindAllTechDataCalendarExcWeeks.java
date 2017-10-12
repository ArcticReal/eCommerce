
package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event.TechDataCalendarExcWeekFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.mapper.TechDataCalendarExcWeekMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.model.TechDataCalendarExcWeek;


public class FindAllTechDataCalendarExcWeeks extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TechDataCalendarExcWeek> returnVal = new ArrayList<TechDataCalendarExcWeek>();
try{
List<GenericValue> results = delegator.findAll("TechDataCalendarExcWeek", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TechDataCalendarExcWeekMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TechDataCalendarExcWeekFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
