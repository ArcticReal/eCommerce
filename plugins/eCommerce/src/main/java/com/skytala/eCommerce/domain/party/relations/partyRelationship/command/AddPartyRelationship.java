package com.skytala.eCommerce.domain.party.relations.partyRelationship.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyRelationship.event.PartyRelationshipAdded;
import com.skytala.eCommerce.domain.party.relations.partyRelationship.mapper.PartyRelationshipMapper;
import com.skytala.eCommerce.domain.party.relations.partyRelationship.model.PartyRelationship;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyRelationship extends Command {

private PartyRelationship elementToBeAdded;
public AddPartyRelationship(PartyRelationship elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyRelationship addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyRelationship", elementToBeAdded.mapAttributeField());
addedElement = PartyRelationshipMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyRelationshipAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
