package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryAssign;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryAssign.WorkEffortInventoryAssignAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryAssign.WorkEffortInventoryAssignMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryAssign.WorkEffortInventoryAssign;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortInventoryAssign extends Command {

private WorkEffortInventoryAssign elementToBeAdded;
public AddWorkEffortInventoryAssign(WorkEffortInventoryAssign elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortInventoryAssign addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortInventoryAssign", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortInventoryAssignMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortInventoryAssignAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
