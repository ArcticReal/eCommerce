package com.skytala.eCommerce.domain.party.relations.party.command.identification;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.identification.PartyIdentificationUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.identification.PartyIdentification;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyIdentification extends Command {

private PartyIdentification elementToBeUpdated;

public UpdatePartyIdentification(PartyIdentification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyIdentification getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyIdentification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyIdentification", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyIdentification.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyIdentification.class);
}
success = false;
}
Event resultingEvent = new PartyIdentificationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
