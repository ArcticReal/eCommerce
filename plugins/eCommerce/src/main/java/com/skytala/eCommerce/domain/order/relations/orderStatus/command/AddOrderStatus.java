package com.skytala.eCommerce.domain.order.relations.orderStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusAdded;
import com.skytala.eCommerce.domain.order.relations.orderStatus.mapper.OrderStatusMapper;
import com.skytala.eCommerce.domain.order.relations.orderStatus.model.OrderStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderStatus extends Command {

private OrderStatus elementToBeAdded;
public AddOrderStatus(OrderStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderStatus addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderStatusId(delegator.getNextSeqId("OrderStatus"));
GenericValue newValue = delegator.makeValue("OrderStatus", elementToBeAdded.mapAttributeField());
addedElement = OrderStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
