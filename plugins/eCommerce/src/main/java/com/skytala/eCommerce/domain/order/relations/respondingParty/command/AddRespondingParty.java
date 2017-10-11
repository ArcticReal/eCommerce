package com.skytala.eCommerce.domain.order.relations.respondingParty.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyAdded;
import com.skytala.eCommerce.domain.order.relations.respondingParty.mapper.RespondingPartyMapper;
import com.skytala.eCommerce.domain.order.relations.respondingParty.model.RespondingParty;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRespondingParty extends Command {

private RespondingParty elementToBeAdded;
public AddRespondingParty(RespondingParty elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RespondingParty addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRespondingPartySeqId(delegator.getNextSeqId("RespondingParty"));
GenericValue newValue = delegator.makeValue("RespondingParty", elementToBeAdded.mapAttributeField());
addedElement = RespondingPartyMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RespondingPartyAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
