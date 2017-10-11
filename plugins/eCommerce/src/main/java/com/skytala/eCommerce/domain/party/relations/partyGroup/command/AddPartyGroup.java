package com.skytala.eCommerce.domain.party.relations.partyGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyGroup.event.PartyGroupAdded;
import com.skytala.eCommerce.domain.party.relations.partyGroup.mapper.PartyGroupMapper;
import com.skytala.eCommerce.domain.party.relations.partyGroup.model.PartyGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyGroup extends Command {

private PartyGroup elementToBeAdded;
public AddPartyGroup(PartyGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyGroup addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyGroup", elementToBeAdded.mapAttributeField());
addedElement = PartyGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
