package com.skytala.eCommerce.domain.order.relations.orderItemBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.event.OrderItemBillingAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.mapper.OrderItemBillingMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.model.OrderItemBilling;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemBilling extends Command {

private OrderItemBilling elementToBeAdded;
public AddOrderItemBilling(OrderItemBilling elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemBilling addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderItemBilling", elementToBeAdded.mapAttributeField());
addedElement = OrderItemBillingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemBillingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
