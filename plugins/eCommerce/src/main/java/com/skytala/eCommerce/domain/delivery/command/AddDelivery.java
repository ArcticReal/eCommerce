package com.skytala.eCommerce.domain.delivery.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.delivery.event.DeliveryAdded;
import com.skytala.eCommerce.domain.delivery.mapper.DeliveryMapper;
import com.skytala.eCommerce.domain.delivery.model.Delivery;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDelivery extends Command {

private Delivery elementToBeAdded;
public AddDelivery(Delivery elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Delivery addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDeliveryId(delegator.getNextSeqId("Delivery"));
GenericValue newValue = delegator.makeValue("Delivery", elementToBeAdded.mapAttributeField());
addedElement = DeliveryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DeliveryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
