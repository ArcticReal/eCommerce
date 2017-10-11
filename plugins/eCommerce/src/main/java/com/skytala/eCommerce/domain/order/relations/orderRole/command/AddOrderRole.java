package com.skytala.eCommerce.domain.order.relations.orderRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderRole.event.OrderRoleAdded;
import com.skytala.eCommerce.domain.order.relations.orderRole.mapper.OrderRoleMapper;
import com.skytala.eCommerce.domain.order.relations.orderRole.model.OrderRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderRole extends Command {

private OrderRole elementToBeAdded;
public AddOrderRole(OrderRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderRole", elementToBeAdded.mapAttributeField());
addedElement = OrderRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
