package com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute.OrderAdjustmentAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.attribute.OrderAdjustmentAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.attribute.OrderAdjustmentAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderAdjustmentAttribute extends Command {

private OrderAdjustmentAttribute elementToBeAdded;
public AddOrderAdjustmentAttribute(OrderAdjustmentAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderAdjustmentAttribute addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("OrderAdjustmentAttribute"));
GenericValue newValue = delegator.makeValue("OrderAdjustmentAttribute", elementToBeAdded.mapAttributeField());
addedElement = OrderAdjustmentAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderAdjustmentAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
