package com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.event.OrderItemGroupOrderAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.mapper.OrderItemGroupOrderMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.model.OrderItemGroupOrder;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemGroupOrder extends Command {

private OrderItemGroupOrder elementToBeAdded;
public AddOrderItemGroupOrder(OrderItemGroupOrder elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemGroupOrder addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemSeqId(delegator.getNextSeqId("OrderItemGroupOrder"));
GenericValue newValue = delegator.makeValue("OrderItemGroupOrder", elementToBeAdded.mapAttributeField());
addedElement = OrderItemGroupOrderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemGroupOrderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
