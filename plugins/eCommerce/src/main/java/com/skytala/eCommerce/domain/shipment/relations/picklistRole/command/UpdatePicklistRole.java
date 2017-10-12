package com.skytala.eCommerce.domain.shipment.relations.picklistRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.picklistRole.event.PicklistRoleUpdated;
import com.skytala.eCommerce.domain.shipment.relations.picklistRole.model.PicklistRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePicklistRole extends Command {

private PicklistRole elementToBeUpdated;

public UpdatePicklistRole(PicklistRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PicklistRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PicklistRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PicklistRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PicklistRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PicklistRole.class);
}
success = false;
}
Event resultingEvent = new PicklistRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
