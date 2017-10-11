package com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event.InventoryItemVarianceUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.model.InventoryItemVariance;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInventoryItemVariance extends Command {

private InventoryItemVariance elementToBeUpdated;

public UpdateInventoryItemVariance(InventoryItemVariance elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InventoryItemVariance getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InventoryItemVariance elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InventoryItemVariance", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InventoryItemVariance.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InventoryItemVariance.class);
}
success = false;
}
Event resultingEvent = new InventoryItemVarianceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
