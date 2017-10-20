package com.skytala.eCommerce.domain.order.relations.orderType.command.attr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderType.mapper.attr.OrderTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderType.model.attr.OrderTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderTypeAttr extends Command {

private OrderTypeAttr elementToBeAdded;
public AddOrderTypeAttr(OrderTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = OrderTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
