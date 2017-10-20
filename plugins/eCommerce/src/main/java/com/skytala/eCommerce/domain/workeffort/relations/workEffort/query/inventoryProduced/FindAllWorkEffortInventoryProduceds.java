
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.inventoryProduced;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryProduced.WorkEffortInventoryProducedMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;


public class FindAllWorkEffortInventoryProduceds extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortInventoryProduced> returnVal = new ArrayList<WorkEffortInventoryProduced>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortInventoryProduced", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortInventoryProducedMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortInventoryProducedFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
