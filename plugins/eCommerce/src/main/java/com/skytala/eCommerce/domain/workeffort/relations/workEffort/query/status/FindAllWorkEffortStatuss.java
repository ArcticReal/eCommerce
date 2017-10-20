
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.status;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.status.WorkEffortStatusMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status.WorkEffortStatus;


public class FindAllWorkEffortStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortStatus> returnVal = new ArrayList<WorkEffortStatus>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
