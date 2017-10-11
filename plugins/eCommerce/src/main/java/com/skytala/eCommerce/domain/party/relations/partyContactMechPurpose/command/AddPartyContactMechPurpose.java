package com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.event.PartyContactMechPurposeAdded;
import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.mapper.PartyContactMechPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.model.PartyContactMechPurpose;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyContactMechPurpose extends Command {

private PartyContactMechPurpose elementToBeAdded;
public AddPartyContactMechPurpose(PartyContactMechPurpose elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyContactMechPurpose addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyContactMechPurpose", elementToBeAdded.mapAttributeField());
addedElement = PartyContactMechPurposeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyContactMechPurposeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
