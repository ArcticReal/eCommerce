package com.skytala.eCommerce.domain.communicationEvent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.communicationEvent.event.CommunicationEventUpdated;
import com.skytala.eCommerce.domain.communicationEvent.model.CommunicationEvent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCommunicationEvent extends Command {

private CommunicationEvent elementToBeUpdated;

public UpdateCommunicationEvent(CommunicationEvent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CommunicationEvent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CommunicationEvent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CommunicationEvent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CommunicationEvent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CommunicationEvent.class);
}
success = false;
}
Event resultingEvent = new CommunicationEventUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
