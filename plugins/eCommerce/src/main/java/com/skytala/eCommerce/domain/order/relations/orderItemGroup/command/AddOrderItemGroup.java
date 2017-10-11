package com.skytala.eCommerce.domain.order.relations.orderItemGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemGroup.event.OrderItemGroupAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemGroup.mapper.OrderItemGroupMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemGroup.model.OrderItemGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemGroup extends Command {

private OrderItemGroup elementToBeAdded;
public AddOrderItemGroup(OrderItemGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemGroupSeqId(delegator.getNextSeqId("OrderItemGroup"));
GenericValue newValue = delegator.makeValue("OrderItemGroup", elementToBeAdded.mapAttributeField());
addedElement = OrderItemGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
