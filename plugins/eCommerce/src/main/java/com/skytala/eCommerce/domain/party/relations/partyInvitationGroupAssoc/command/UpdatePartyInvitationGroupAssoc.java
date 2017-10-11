package com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.event.PartyInvitationGroupAssocUpdated;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.model.PartyInvitationGroupAssoc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyInvitationGroupAssoc extends Command {

private PartyInvitationGroupAssoc elementToBeUpdated;

public UpdatePartyInvitationGroupAssoc(PartyInvitationGroupAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyInvitationGroupAssoc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyInvitationGroupAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyInvitationGroupAssoc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyInvitationGroupAssoc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyInvitationGroupAssoc.class);
}
success = false;
}
Event resultingEvent = new PartyInvitationGroupAssocUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
