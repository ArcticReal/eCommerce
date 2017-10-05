
package com.skytala.eCommerce.domain.timeEntry.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.timeEntry.event.TimeEntryFound;
import com.skytala.eCommerce.domain.timeEntry.mapper.TimeEntryMapper;
import com.skytala.eCommerce.domain.timeEntry.model.TimeEntry;


public class FindAllTimeEntrys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TimeEntry> returnVal = new ArrayList<TimeEntry>();
try{
List<GenericValue> results = delegator.findAll("TimeEntry", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TimeEntryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TimeEntryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
