package com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.billing;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.billing.OrderAdjustmentBillingAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.billing.OrderAdjustmentBillingMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.billing.OrderAdjustmentBilling;
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
