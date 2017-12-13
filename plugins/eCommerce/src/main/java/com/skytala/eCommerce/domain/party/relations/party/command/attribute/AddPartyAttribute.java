package com.skytala.eCommerce.domain.party.relations.party.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.attribute.PartyAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.attribute.PartyAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.attribute.PartyAttribute;
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
elementToBeAdded.setAttrName(delegator.getNextSeqId("PartyAttribute"));
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
