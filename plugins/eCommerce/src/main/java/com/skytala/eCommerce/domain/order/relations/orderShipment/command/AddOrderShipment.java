package com.skytala.eCommerce.domain.order.relations.orderShipment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderShipment.event.OrderShipmentAdded;
import com.skytala.eCommerce.domain.order.relations.orderShipment.mapper.OrderShipmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderShipment extends Command {

private OrderShipment elementToBeAdded;
public AddOrderShipment(OrderShipment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderShipment addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderShipment", elementToBeAdded.mapAttributeField());
addedElement = OrderShipmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderShipmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
