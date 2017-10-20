package com.skytala.eCommerce.domain.product.relations.inventoryItem.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.status.InventoryItemStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInventoryItemStatus extends Command {

private InventoryItemStatus elementToBeUpdated;

public UpdateInventoryItemStatus(InventoryItemStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InventoryItemStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InventoryItemStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InventoryItemStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InventoryItemStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InventoryItemStatus.class);
}
success = false;
}
Event resultingEvent = new InventoryItemStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
