package com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event.CommunicationEventPurposeUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.model.CommunicationEventPurpose;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCommunicationEventPurpose extends Command {

private CommunicationEventPurpose elementToBeUpdated;

public UpdateCommunicationEventPurpose(CommunicationEventPurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CommunicationEventPurpose getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CommunicationEventPurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CommunicationEventPurpose", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CommunicationEventPurpose.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CommunicationEventPurpose.class);
}
success = false;
}
Event resultingEvent = new CommunicationEventPurposeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
