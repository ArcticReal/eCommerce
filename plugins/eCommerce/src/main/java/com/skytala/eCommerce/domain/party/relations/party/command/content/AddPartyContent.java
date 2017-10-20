package com.skytala.eCommerce.domain.party.relations.party.command.content;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.content.PartyContentAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.content.PartyContentMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.content.PartyContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyContent extends Command {

private PartyContent elementToBeAdded;
public AddPartyContent(PartyContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyContent", elementToBeAdded.mapAttributeField());
addedElement = PartyContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
