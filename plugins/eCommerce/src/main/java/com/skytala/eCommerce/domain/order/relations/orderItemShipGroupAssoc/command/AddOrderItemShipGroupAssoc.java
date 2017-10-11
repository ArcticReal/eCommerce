package com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.event.OrderItemShipGroupAssocAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.mapper.OrderItemShipGroupAssocMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.model.OrderItemShipGroupAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemShipGroupAssoc extends Command {

private OrderItemShipGroupAssoc elementToBeAdded;
public AddOrderItemShipGroupAssoc(OrderItemShipGroupAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemShipGroupAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderItemShipGroupAssoc", elementToBeAdded.mapAttributeField());
addedElement = OrderItemShipGroupAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemShipGroupAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
