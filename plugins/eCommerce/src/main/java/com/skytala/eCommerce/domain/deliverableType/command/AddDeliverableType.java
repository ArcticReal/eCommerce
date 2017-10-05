package com.skytala.eCommerce.domain.deliverableType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.deliverableType.event.DeliverableTypeAdded;
import com.skytala.eCommerce.domain.deliverableType.mapper.DeliverableTypeMapper;
import com.skytala.eCommerce.domain.deliverableType.model.DeliverableType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDeliverableType extends Command {

private DeliverableType elementToBeAdded;
public AddDeliverableType(DeliverableType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DeliverableType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDeliverableTypeId(delegator.getNextSeqId("DeliverableType"));
GenericValue newValue = delegator.makeValue("DeliverableType", elementToBeAdded.mapAttributeField());
addedElement = DeliverableTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DeliverableTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
