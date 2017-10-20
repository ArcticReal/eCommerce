package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryProduced;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryProduced.WorkEffortInventoryProducedMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortInventoryProduced extends Command {

private WorkEffortInventoryProduced elementToBeAdded;
public AddWorkEffortInventoryProduced(WorkEffortInventoryProduced elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortInventoryProduced addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortInventoryProduced", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortInventoryProducedMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortInventoryProducedAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
