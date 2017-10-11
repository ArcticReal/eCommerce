package com.skytala.eCommerce.domain.party.relations.partyClassificationGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyClassificationGroup.event.PartyClassificationGroupAdded;
import com.skytala.eCommerce.domain.party.relations.partyClassificationGroup.mapper.PartyClassificationGroupMapper;
import com.skytala.eCommerce.domain.party.relations.partyClassificationGroup.model.PartyClassificationGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyClassificationGroup extends Command {

private PartyClassificationGroup elementToBeAdded;
public AddPartyClassificationGroup(PartyClassificationGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyClassificationGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyClassificationGroupId(delegator.getNextSeqId("PartyClassificationGroup"));
GenericValue newValue = delegator.makeValue("PartyClassificationGroup", elementToBeAdded.mapAttributeField());
addedElement = PartyClassificationGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyClassificationGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
