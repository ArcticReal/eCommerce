
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.eventReminder;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder.WorkEffortEventReminderFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.eventReminder.WorkEffortEventReminderMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.eventReminder.WorkEffortEventReminder;


public class FindAllWorkEffortEventReminders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortEventReminder> returnVal = new ArrayList<WorkEffortEventReminder>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortEventReminder", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortEventReminderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortEventReminderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
