package com.skytala.eCommerce.domain.party.relations.party.command.invitation;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.invitation.PartyInvitation;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyInvitation extends Command {

private PartyInvitation elementToBeUpdated;

public UpdatePartyInvitation(PartyInvitation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyInvitation getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyInvitation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyInvitation", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyInvitation.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyInvitation.class);
}
success = false;
}
Event resultingEvent = new PartyInvitationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
