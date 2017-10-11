package com.skytala.eCommerce.domain.product.relations.inventoryItemLabelType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelType.event.InventoryItemLabelTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelType.model.InventoryItemLabelType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInventoryItemLabelType extends Command {

private InventoryItemLabelType elementToBeUpdated;

public UpdateInventoryItemLabelType(InventoryItemLabelType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InventoryItemLabelType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InventoryItemLabelType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InventoryItemLabelType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InventoryItemLabelType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InventoryItemLabelType.class);
}
success = false;
}
Event resultingEvent = new InventoryItemLabelTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
