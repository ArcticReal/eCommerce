package com.skytala.eCommerce.domain.physicalInventory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.physicalInventory.event.PhysicalInventoryUpdated;
import com.skytala.eCommerce.domain.physicalInventory.model.PhysicalInventory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePhysicalInventory extends Command {

private PhysicalInventory elementToBeUpdated;

public UpdatePhysicalInventory(PhysicalInventory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PhysicalInventory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PhysicalInventory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PhysicalInventory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PhysicalInventory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PhysicalInventory.class);
}
success = false;
}
Event resultingEvent = new PhysicalInventoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
