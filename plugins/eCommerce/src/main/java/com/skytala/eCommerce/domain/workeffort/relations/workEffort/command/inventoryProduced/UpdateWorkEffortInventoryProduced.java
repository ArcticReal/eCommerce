package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryProduced;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortInventoryProduced extends Command {

private WorkEffortInventoryProduced elementToBeUpdated;

public UpdateWorkEffortInventoryProduced(WorkEffortInventoryProduced elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortInventoryProduced getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortInventoryProduced elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortInventoryProduced", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortInventoryProduced.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortInventoryProduced.class);
}
success = false;
}
Event resultingEvent = new WorkEffortInventoryProducedUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
