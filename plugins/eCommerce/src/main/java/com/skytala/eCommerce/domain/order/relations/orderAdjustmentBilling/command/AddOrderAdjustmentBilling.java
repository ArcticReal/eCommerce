package com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.event.OrderAdjustmentBillingAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.mapper.OrderAdjustmentBillingMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.model.OrderAdjustmentBilling;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderAdjustmentBilling extends Command {

private OrderAdjustmentBilling elementToBeAdded;
public AddOrderAdjustmentBilling(OrderAdjustmentBilling elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderAdjustmentBilling addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceItemSeqId(delegator.getNextSeqId("OrderAdjustmentBilling"));
GenericValue newValue = delegator.makeValue("OrderAdjustmentBilling", elementToBeAdded.mapAttributeField());
addedElement = OrderAdjustmentBillingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderAdjustmentBillingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
