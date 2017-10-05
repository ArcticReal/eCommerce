package com.skytala.eCommerce.domain.deliverable.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.deliverable.event.DeliverableAdded;
import com.skytala.eCommerce.domain.deliverable.mapper.DeliverableMapper;
import com.skytala.eCommerce.domain.deliverable.model.Deliverable;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDeliverable extends Command {

private Deliverable elementToBeAdded;
public AddDeliverable(Deliverable elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Deliverable addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDeliverableId(delegator.getNextSeqId("Deliverable"));
GenericValue newValue = delegator.makeValue("Deliverable", elementToBeAdded.mapAttributeField());
addedElement = DeliverableMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DeliverableAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
