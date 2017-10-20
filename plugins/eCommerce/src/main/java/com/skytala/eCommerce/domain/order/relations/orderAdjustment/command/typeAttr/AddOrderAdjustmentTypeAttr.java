package com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.typeAttr.OrderAdjustmentTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.typeAttr.OrderAdjustmentTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.typeAttr.OrderAdjustmentTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderAdjustmentTypeAttr extends Command {

private OrderAdjustmentTypeAttr elementToBeAdded;
public AddOrderAdjustmentTypeAttr(OrderAdjustmentTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderAdjustmentTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderAdjustmentTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = OrderAdjustmentTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderAdjustmentTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
