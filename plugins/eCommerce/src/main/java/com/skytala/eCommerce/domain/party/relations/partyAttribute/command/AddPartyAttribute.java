package com.skytala.eCommerce.domain.party.relations.partyAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.event.PartyAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.mapper.PartyAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.model.PartyAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyAttribute extends Command {

private PartyAttribute elementToBeAdded;
public AddPartyAttribute(PartyAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyAttribute", elementToBeAdded.mapAttributeField());
addedElement = PartyAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
