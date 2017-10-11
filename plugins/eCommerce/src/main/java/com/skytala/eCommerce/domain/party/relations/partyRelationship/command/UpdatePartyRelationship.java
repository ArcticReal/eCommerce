package com.skytala.eCommerce.domain.party.relations.partyRelationship.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyRelationship.event.PartyRelationshipUpdated;
import com.skytala.eCommerce.domain.party.relations.partyRelationship.model.PartyRelationship;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyRelationship extends Command {

private PartyRelationship elementToBeUpdated;

public UpdatePartyRelationship(PartyRelationship elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyRelationship getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyRelationship elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyRelationship", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyRelationship.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyRelationship.class);
}
success = false;
}
Event resultingEvent = new PartyRelationshipUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
