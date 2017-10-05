
package com.skytala.eCommerce.domain.workEffort.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workEffort.event.WorkEffortFound;
import com.skytala.eCommerce.domain.workEffort.mapper.WorkEffortMapper;
import com.skytala.eCommerce.domain.workEffort.model.WorkEffort;


public class FindAllWorkEfforts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffort> returnVal = new ArrayList<WorkEffort>();
try{
List<GenericValue> results = delegator.findAll("WorkEffort", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
