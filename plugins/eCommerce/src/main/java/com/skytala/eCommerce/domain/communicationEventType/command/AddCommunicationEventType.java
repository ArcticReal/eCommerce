package com.skytala.eCommerce.domain.communicationEventType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.communicationEventType.event.CommunicationEventTypeAdded;
import com.skytala.eCommerce.domain.communicationEventType.mapper.CommunicationEventTypeMapper;
import com.skytala.eCommerce.domain.communicationEventType.model.CommunicationEventType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommunicationEventType extends Command {

private CommunicationEventType elementToBeAdded;
public AddCommunicationEventType(CommunicationEventType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommunicationEventType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCommunicationEventTypeId(delegator.getNextSeqId("CommunicationEventType"));
GenericValue newValue = delegator.makeValue("CommunicationEventType", elementToBeAdded.mapAttributeField());
addedElement = CommunicationEventTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommunicationEventTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
