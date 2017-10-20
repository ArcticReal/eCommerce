package com.skytala.eCommerce.domain.party.relations.party.command.classification;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classification.PartyClassificationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classification.PartyClassification;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyClassification extends Command {

private PartyClassification elementToBeAdded;
public AddPartyClassification(PartyClassification elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyClassification addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyClassification", elementToBeAdded.mapAttributeField());
addedElement = PartyClassificationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyClassificationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
