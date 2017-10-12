
package com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.event.WorkEffortInventoryAssignFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.mapper.WorkEffortInventoryAssignMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.model.WorkEffortInventoryAssign;


public class FindAllWorkEffortInventoryAssigns extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortInventoryAssign> returnVal = new ArrayList<WorkEffortInventoryAssign>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortInventoryAssign", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortInventoryAssignMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortInventoryAssignFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
