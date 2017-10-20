package com.skytala.eCommerce.domain.product.relations.inventoryItem.command.tempRes;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes.InventoryItemTempResUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes.InventoryItemTempRes;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInventoryItemTempRes extends Command {

private InventoryItemTempRes elementToBeUpdated;

public UpdateInventoryItemTempRes(InventoryItemTempRes elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InventoryItemTempRes getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InventoryItemTempRes elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InventoryItemTempRes", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InventoryItemTempRes.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InventoryItemTempRes.class);
}
success = false;
}
Event resultingEvent = new InventoryItemTempResUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
