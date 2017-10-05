package com.skytala.eCommerce.domain.deliverable.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.deliverable.event.DeliverableUpdated;
import com.skytala.eCommerce.domain.deliverable.model.Deliverable;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDeliverable extends Command {

private Deliverable elementToBeUpdated;

public UpdateDeliverable(Deliverable elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Deliverable getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Deliverable elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Deliverable", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Deliverable.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Deliverable.class);
}
success = false;
}
Event resultingEvent = new DeliverableUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
