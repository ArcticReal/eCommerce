package com.skytala.eCommerce.domain.party.relations.partyAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.event.PartyAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.model.PartyAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyAttribute extends Command {

private PartyAttribute elementToBeUpdated;

public UpdatePartyAttribute(PartyAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyAttribute.class);
}
success = false;
}
Event resultingEvent = new PartyAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
