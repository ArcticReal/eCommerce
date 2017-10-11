package com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.event.CommunicationEventPrpTypUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.model.CommunicationEventPrpTyp;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCommunicationEventPrpTyp extends Command {

private CommunicationEventPrpTyp elementToBeUpdated;

public UpdateCommunicationEventPrpTyp(CommunicationEventPrpTyp elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CommunicationEventPrpTyp getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CommunicationEventPrpTyp elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CommunicationEventPrpTyp", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CommunicationEventPrpTyp.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CommunicationEventPrpTyp.class);
}
success = false;
}
Event resultingEvent = new CommunicationEventPrpTypUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
