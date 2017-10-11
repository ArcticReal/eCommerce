package com.skytala.eCommerce.domain.order.relations.orderItemAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.event.OrderItemAssocAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.mapper.OrderItemAssocMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.model.OrderItemAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemAssoc extends Command {

private OrderItemAssoc elementToBeAdded;
public AddOrderItemAssoc(OrderItemAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderItemAssoc", elementToBeAdded.mapAttributeField());
addedElement = OrderItemAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
