package com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.type.OrderAdjustmentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.type.OrderAdjustmentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderAdjustmentType extends Command {

private OrderAdjustmentType elementToBeAdded;
public AddOrderAdjustmentType(OrderAdjustmentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderAdjustmentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderAdjustmentTypeId(delegator.getNextSeqId("OrderAdjustmentType"));
GenericValue newValue = delegator.makeValue("OrderAdjustmentType", elementToBeAdded.mapAttributeField());
addedElement = OrderAdjustmentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderAdjustmentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
