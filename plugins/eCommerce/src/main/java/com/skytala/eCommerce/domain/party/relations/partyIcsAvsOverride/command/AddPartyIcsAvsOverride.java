package com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.event.PartyIcsAvsOverrideAdded;
import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.mapper.PartyIcsAvsOverrideMapper;
import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.model.PartyIcsAvsOverride;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyIcsAvsOverride extends Command {

private PartyIcsAvsOverride elementToBeAdded;
public AddPartyIcsAvsOverride(PartyIcsAvsOverride elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyIcsAvsOverride addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyIcsAvsOverride", elementToBeAdded.mapAttributeField());
addedElement = PartyIcsAvsOverrideMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyIcsAvsOverrideAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
