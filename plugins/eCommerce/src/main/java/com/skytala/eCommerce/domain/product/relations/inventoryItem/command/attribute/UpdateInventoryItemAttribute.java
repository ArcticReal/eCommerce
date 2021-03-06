package com.skytala.eCommerce.domain.product.relations.inventoryItem.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.attribute.InventoryItemAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.attribute.InventoryItemAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInventoryItemAttribute extends Command {

private InventoryItemAttribute elementToBeUpdated;

public UpdateInventoryItemAttribute(InventoryItemAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InventoryItemAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InventoryItemAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InventoryItemAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InventoryItemAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InventoryItemAttribute.class);
}
success = false;
}
Event resultingEvent = new InventoryItemAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
