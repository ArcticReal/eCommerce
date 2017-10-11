package com.skytala.eCommerce.domain.order.relations.orderAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.mapper.OrderAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderAttribute extends Command {

private OrderAttribute elementToBeAdded;
public AddOrderAttribute(OrderAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderAttribute", elementToBeAdded.mapAttributeField());
addedElement = OrderAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}