package com.skytala.eCommerce.domain.party.relations.partyType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyType.event.PartyTypeAdded;
import com.skytala.eCommerce.domain.party.relations.partyType.mapper.PartyTypeMapper;
import com.skytala.eCommerce.domain.party.relations.partyType.model.PartyType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyType extends Command {

private PartyType elementToBeAdded;
public AddPartyType(PartyType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyTypeId(delegator.getNextSeqId("PartyType"));
GenericValue newValue = delegator.makeValue("PartyType", elementToBeAdded.mapAttributeField());
addedElement = PartyTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
