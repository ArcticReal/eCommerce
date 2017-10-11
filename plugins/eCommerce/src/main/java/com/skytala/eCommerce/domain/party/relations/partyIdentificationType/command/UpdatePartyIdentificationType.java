package com.skytala.eCommerce.domain.party.relations.partyIdentificationType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyIdentificationType.event.PartyIdentificationTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.partyIdentificationType.model.PartyIdentificationType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyIdentificationType extends Command {

private PartyIdentificationType elementToBeUpdated;

public UpdatePartyIdentificationType(PartyIdentificationType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyIdentificationType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyIdentificationType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyIdentificationType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyIdentificationType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyIdentificationType.class);
}
success = false;
}
Event resultingEvent = new PartyIdentificationTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
