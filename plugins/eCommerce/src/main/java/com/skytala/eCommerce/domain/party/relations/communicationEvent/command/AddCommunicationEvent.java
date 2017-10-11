package com.skytala.eCommerce.domain.party.relations.communicationEvent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.CommunicationEventAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.CommunicationEventMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.CommunicationEvent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommunicationEvent extends Command {

private CommunicationEvent elementToBeAdded;
public AddCommunicationEvent(CommunicationEvent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommunicationEvent addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCommunicationEventId(delegator.getNextSeqId("CommunicationEvent"));
GenericValue newValue = delegator.makeValue("CommunicationEvent", elementToBeAdded.mapAttributeField());
addedElement = CommunicationEventMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommunicationEventAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
