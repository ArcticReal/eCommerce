package com.skytala.eCommerce.domain.party.relations.party.command.relationshipType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.relationshipType.PartyRelationshipTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.relationshipType.PartyRelationshipType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyRelationshipType extends Command {

private PartyRelationshipType elementToBeAdded;
public AddPartyRelationshipType(PartyRelationshipType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyRelationshipType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyRelationshipTypeId(delegator.getNextSeqId("PartyRelationshipType"));
GenericValue newValue = delegator.makeValue("PartyRelationshipType", elementToBeAdded.mapAttributeField());
addedElement = PartyRelationshipTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyRelationshipTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
