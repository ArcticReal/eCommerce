package com.skytala.eCommerce.domain.shipment.relations.picklistItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.picklistItem.event.PicklistItemUpdated;
import com.skytala.eCommerce.domain.shipment.relations.picklistItem.model.PicklistItem;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePicklistItem extends Command {

private PicklistItem elementToBeUpdated;

public UpdatePicklistItem(PicklistItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PicklistItem getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PicklistItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PicklistItem", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PicklistItem.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PicklistItem.class);
}
success = false;
}
Event resultingEvent = new PicklistItemUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
