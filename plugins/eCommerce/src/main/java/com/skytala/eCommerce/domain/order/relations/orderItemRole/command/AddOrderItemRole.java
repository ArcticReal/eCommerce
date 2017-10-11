package com.skytala.eCommerce.domain.order.relations.orderItemRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemRole.event.OrderItemRoleAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemRole.mapper.OrderItemRoleMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemRole.model.OrderItemRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemRole extends Command {

private OrderItemRole elementToBeAdded;
public AddOrderItemRole(OrderItemRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemSeqId(delegator.getNextSeqId("OrderItemRole"));
GenericValue newValue = delegator.makeValue("OrderItemRole", elementToBeAdded.mapAttributeField());
addedElement = OrderItemRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
