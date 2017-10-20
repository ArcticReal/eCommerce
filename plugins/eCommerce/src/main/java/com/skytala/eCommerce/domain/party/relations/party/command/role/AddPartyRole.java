package com.skytala.eCommerce.domain.party.relations.party.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.role.PartyRoleAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.role.PartyRoleMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.role.PartyRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyRole extends Command {

private PartyRole elementToBeAdded;
public AddPartyRole(PartyRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyRole", elementToBeAdded.mapAttributeField());
addedElement = PartyRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
