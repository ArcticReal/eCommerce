package com.skytala.eCommerce.domain.order.relations.orderTermAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderTermAttribute.event.OrderTermAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderTermAttribute.mapper.OrderTermAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderTermAttribute.model.OrderTermAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderTermAttribute extends Command {

private OrderTermAttribute elementToBeAdded;
public AddOrderTermAttribute(OrderTermAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderTermAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderTermAttribute", elementToBeAdded.mapAttributeField());
addedElement = OrderTermAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderTermAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
