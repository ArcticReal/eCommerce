package com.skytala.eCommerce.domain.order.relations.orderItemAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event.OrderItemAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.mapper.OrderItemAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.model.OrderItemAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemAttribute extends Command {

private OrderItemAttribute elementToBeAdded;
public AddOrderItemAttribute(OrderItemAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemAttribute addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemSeqId(delegator.getNextSeqId("OrderItemAttribute"));
GenericValue newValue = delegator.makeValue("OrderItemAttribute", elementToBeAdded.mapAttributeField());
addedElement = OrderItemAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
