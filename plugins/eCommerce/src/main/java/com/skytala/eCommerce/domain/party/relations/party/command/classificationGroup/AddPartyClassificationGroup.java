package com.skytala.eCommerce.domain.party.relations.party.command.classificationGroup;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationGroup.PartyClassificationGroupAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classificationGroup.PartyClassificationGroupMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classificationGroup.PartyClassificationGroup;
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
