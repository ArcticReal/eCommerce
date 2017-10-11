package com.skytala.eCommerce.domain.party.relations.partyNeed.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyNeed.event.PartyNeedAdded;
import com.skytala.eCommerce.domain.party.relations.partyNeed.mapper.PartyNeedMapper;
import com.skytala.eCommerce.domain.party.relations.partyNeed.model.PartyNeed;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyNeed extends Command {

private PartyNeed elementToBeAdded;
public AddPartyNeed(PartyNeed elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyNeed addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyNeedId(delegator.getNextSeqId("PartyNeed"));
GenericValue newValue = delegator.makeValue("PartyNeed", elementToBeAdded.mapAttributeField());
addedElement = PartyNeedMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyNeedAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
