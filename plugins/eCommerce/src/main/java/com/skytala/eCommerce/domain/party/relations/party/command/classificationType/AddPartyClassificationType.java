package com.skytala.eCommerce.domain.party.relations.party.command.classificationType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classificationType.PartyClassificationTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classificationType.PartyClassificationType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyClassificationType extends Command {

private PartyClassificationType elementToBeAdded;
public AddPartyClassificationType(PartyClassificationType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyClassificationType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyClassificationTypeId(delegator.getNextSeqId("PartyClassificationType"));
GenericValue newValue = delegator.makeValue("PartyClassificationType", elementToBeAdded.mapAttributeField());
addedElement = PartyClassificationTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyClassificationTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
