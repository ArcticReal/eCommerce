
package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.query.excDay;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excDay.TechDataCalendarExcDayFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.excDay.TechDataCalendarExcDayMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excDay.TechDataCalendarExcDay;


public class FindAllTechDataCalendarExcDays extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TechDataCalendarExcDay> returnVal = new ArrayList<TechDataCalendarExcDay>();
try{
List<GenericValue> results = delegator.findAll("TechDataCalendarExcDay", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TechDataCalendarExcDayMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TechDataCalendarExcDayFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
