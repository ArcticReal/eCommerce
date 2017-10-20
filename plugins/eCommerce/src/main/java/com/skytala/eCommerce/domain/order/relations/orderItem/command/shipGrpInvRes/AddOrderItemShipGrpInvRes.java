package com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGrpInvRes;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGrpInvRes.OrderItemShipGrpInvResMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGrpInvRes.OrderItemShipGrpInvRes;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemShipGrpInvRes extends Command {

private OrderItemShipGrpInvRes elementToBeAdded;
public AddOrderItemShipGrpInvRes(OrderItemShipGrpInvRes elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemShipGrpInvRes addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderItemShipGrpInvRes", elementToBeAdded.mapAttributeField());
addedElement = OrderItemShipGrpInvResMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemShipGrpInvResAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
