package com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event.CommunicationEventPurposeAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.mapper.CommunicationEventPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.model.CommunicationEventPurpose;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommunicationEventPurpose extends Command {

private CommunicationEventPurpose elementToBeAdded;
public AddCommunicationEventPurpose(CommunicationEventPurpose elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommunicationEventPurpose addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CommunicationEventPurpose", elementToBeAdded.mapAttributeField());
addedElement = CommunicationEventPurposeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommunicationEventPurposeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
