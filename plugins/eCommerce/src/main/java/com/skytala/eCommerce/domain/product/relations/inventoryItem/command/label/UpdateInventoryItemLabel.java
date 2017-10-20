package com.skytala.eCommerce.domain.product.relations.inventoryItem.command.label;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label.InventoryItemLabelUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.label.InventoryItemLabel;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInventoryItemLabel extends Command {

private InventoryItemLabel elementToBeUpdated;

public UpdateInventoryItemLabel(InventoryItemLabel elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InventoryItemLabel getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InventoryItemLabel elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InventoryItemLabel", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InventoryItemLabel.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InventoryItemLabel.class);
}
success = false;
}
Event resultingEvent = new InventoryItemLabelUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
