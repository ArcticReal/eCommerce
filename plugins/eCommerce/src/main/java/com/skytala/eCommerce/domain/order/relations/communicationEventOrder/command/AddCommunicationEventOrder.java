package com.skytala.eCommerce.domain.order.relations.communicationEventOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event.CommunicationEventOrderAdded;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.mapper.CommunicationEventOrderMapper;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model.CommunicationEventOrder;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommunicationEventOrder extends Command {

private CommunicationEventOrder elementToBeAdded;
public AddCommunicationEventOrder(CommunicationEventOrder elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommunicationEventOrder addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CommunicationEventOrder", elementToBeAdded.mapAttributeField());
addedElement = CommunicationEventOrderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommunicationEventOrderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
