package com.skytala.eCommerce.domain.party.relations.partyStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyStatus.event.PartyStatusAdded;
import com.skytala.eCommerce.domain.party.relations.partyStatus.mapper.PartyStatusMapper;
import com.skytala.eCommerce.domain.party.relations.partyStatus.model.PartyStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyStatus extends Command {

private PartyStatus elementToBeAdded;
public AddPartyStatus(PartyStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyStatus", elementToBeAdded.mapAttributeField());
addedElement = PartyStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
