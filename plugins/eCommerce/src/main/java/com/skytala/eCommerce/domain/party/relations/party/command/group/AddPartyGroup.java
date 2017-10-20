package com.skytala.eCommerce.domain.party.relations.party.command.group;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.group.PartyGroupAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.group.PartyGroupMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.group.PartyGroup;
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
