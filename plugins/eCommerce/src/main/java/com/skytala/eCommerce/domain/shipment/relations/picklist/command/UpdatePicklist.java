package com.skytala.eCommerce.domain.shipment.relations.picklist.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.PicklistUpdated;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.Picklist;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePicklist extends Command {

private Picklist elementToBeUpdated;

public UpdatePicklist(Picklist elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Picklist getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Picklist elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Picklist", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Picklist.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Picklist.class);
}
success = false;
}
Event resultingEvent = new PicklistUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
