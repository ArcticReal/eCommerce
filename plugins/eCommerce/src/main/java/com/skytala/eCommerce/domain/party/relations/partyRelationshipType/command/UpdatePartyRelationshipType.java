package com.skytala.eCommerce.domain.party.relations.partyRelationshipType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyRelationshipType.event.PartyRelationshipTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.partyRelationshipType.model.PartyRelationshipType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyRelationshipType extends Command {

private PartyRelationshipType elementToBeUpdated;

public UpdatePartyRelationshipType(PartyRelationshipType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyRelationshipType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyRelationshipType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyRelationshipType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyRelationshipType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyRelationshipType.class);
}
success = false;
}
Event resultingEvent = new PartyRelationshipTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
