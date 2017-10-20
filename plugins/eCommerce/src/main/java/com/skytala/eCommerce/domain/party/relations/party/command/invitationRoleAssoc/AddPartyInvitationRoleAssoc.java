package com.skytala.eCommerce.domain.party.relations.party.command.invitationRoleAssoc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationRoleAssoc.PartyInvitationRoleAssocMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc.PartyInvitationRoleAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyInvitationRoleAssoc extends Command {

private PartyInvitationRoleAssoc elementToBeAdded;
public AddPartyInvitationRoleAssoc(PartyInvitationRoleAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyInvitationRoleAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyInvitationRoleAssoc", elementToBeAdded.mapAttributeField());
addedElement = PartyInvitationRoleAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyInvitationRoleAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
