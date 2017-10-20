package com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroup;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup.OrderItemShipGroupAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGroup.OrderItemShipGroupMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroup.OrderItemShipGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemShipGroup extends Command {

private OrderItemShipGroup elementToBeAdded;
public AddOrderItemShipGroup(OrderItemShipGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemShipGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipGroupSeqId(delegator.getNextSeqId("OrderItemShipGroup"));
GenericValue newValue = delegator.makeValue("OrderItemShipGroup", elementToBeAdded.mapAttributeField());
addedElement = OrderItemShipGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemShipGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
