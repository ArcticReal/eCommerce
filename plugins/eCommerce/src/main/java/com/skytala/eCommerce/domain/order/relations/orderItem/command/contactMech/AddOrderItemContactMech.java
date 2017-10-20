package com.skytala.eCommerce.domain.order.relations.orderItem.command.contactMech;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.contactMech.OrderItemContactMechAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.contactMech.OrderItemContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.contactMech.OrderItemContactMech;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemContactMech extends Command {

private OrderItemContactMech elementToBeAdded;
public AddOrderItemContactMech(OrderItemContactMech elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemContactMech addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemSeqId(delegator.getNextSeqId("OrderItemContactMech"));
GenericValue newValue = delegator.makeValue("OrderItemContactMech", elementToBeAdded.mapAttributeField());
addedElement = OrderItemContactMechMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemContactMechAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
