package com.skytala.eCommerce.domain.party.relations.party.command.invitation;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitation.PartyInvitationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitation.PartyInvitation;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyInvitation extends Command {

private PartyInvitation elementToBeAdded;
public AddPartyInvitation(PartyInvitation elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyInvitation addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyInvitationId(delegator.getNextSeqId("PartyInvitation"));
GenericValue newValue = delegator.makeValue("PartyInvitation", elementToBeAdded.mapAttributeField());
addedElement = PartyInvitationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyInvitationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
