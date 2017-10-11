package com.skytala.eCommerce.domain.party.relations.partyType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyType.event.PartyTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.partyType.model.PartyType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyType extends Command {

private PartyType elementToBeUpdated;

public UpdatePartyType(PartyType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyType.class);
}
success = false;
}
Event resultingEvent = new PartyTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
