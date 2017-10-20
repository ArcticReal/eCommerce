package com.skytala.eCommerce.domain.party.relations.party.command.contentType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.contentType.PartyContentTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.contentType.PartyContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyContentType extends Command {

private PartyContentType elementToBeAdded;
public AddPartyContentType(PartyContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyContentTypeId(delegator.getNextSeqId("PartyContentType"));
GenericValue newValue = delegator.makeValue("PartyContentType", elementToBeAdded.mapAttributeField());
addedElement = PartyContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
