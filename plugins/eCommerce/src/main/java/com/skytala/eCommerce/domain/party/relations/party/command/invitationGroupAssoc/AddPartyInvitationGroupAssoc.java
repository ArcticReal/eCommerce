package com.skytala.eCommerce.domain.party.relations.party.command.invitationGroupAssoc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc.PartyInvitationGroupAssocAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationGroupAssoc.PartyInvitationGroupAssocMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitationGroupAssoc.PartyInvitationGroupAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyInvitationGroupAssoc extends Command {

private PartyInvitationGroupAssoc elementToBeAdded;
public AddPartyInvitationGroupAssoc(PartyInvitationGroupAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyInvitationGroupAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyInvitationGroupAssoc", elementToBeAdded.mapAttributeField());
addedElement = PartyInvitationGroupAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyInvitationGroupAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
