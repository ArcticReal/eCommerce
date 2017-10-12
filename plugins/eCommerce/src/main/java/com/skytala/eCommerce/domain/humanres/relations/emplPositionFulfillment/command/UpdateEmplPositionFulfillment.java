package com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.event.EmplPositionFulfillmentUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.model.EmplPositionFulfillment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplPositionFulfillment extends Command {

private EmplPositionFulfillment elementToBeUpdated;

public UpdateEmplPositionFulfillment(EmplPositionFulfillment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplPositionFulfillment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplPositionFulfillment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplPositionFulfillment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplPositionFulfillment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplPositionFulfillment.class);
}
success = false;
}
Event resultingEvent = new EmplPositionFulfillmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
