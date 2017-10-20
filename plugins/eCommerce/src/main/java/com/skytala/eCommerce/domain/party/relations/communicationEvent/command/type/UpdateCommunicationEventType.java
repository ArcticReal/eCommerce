package com.skytala.eCommerce.domain.party.relations.communicationEvent.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.type.CommunicationEventTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.type.CommunicationEventType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCommunicationEventType extends Command {

private CommunicationEventType elementToBeUpdated;

public UpdateCommunicationEventType(CommunicationEventType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CommunicationEventType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CommunicationEventType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CommunicationEventType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CommunicationEventType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CommunicationEventType.class);
}
success = false;
}
Event resultingEvent = new CommunicationEventTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
