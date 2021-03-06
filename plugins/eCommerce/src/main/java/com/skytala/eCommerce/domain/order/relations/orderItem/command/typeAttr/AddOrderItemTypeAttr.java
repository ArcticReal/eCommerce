package com.skytala.eCommerce.domain.order.relations.orderItem.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.typeAttr.OrderItemTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.typeAttr.OrderItemTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemTypeAttr extends Command {

private OrderItemTypeAttr elementToBeAdded;
public AddOrderItemTypeAttr(OrderItemTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemTypeAttr addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("OrderItemTypeAttr"));
GenericValue newValue = delegator.makeValue("OrderItemTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = OrderItemTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
