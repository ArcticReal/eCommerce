package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event.OrderProductPromoCodeAdded;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.mapper.OrderProductPromoCodeMapper;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderProductPromoCode extends Command {

private OrderProductPromoCode elementToBeAdded;
public AddOrderProductPromoCode(OrderProductPromoCode elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderProductPromoCode addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderProductPromoCode", elementToBeAdded.mapAttributeField());
addedElement = OrderProductPromoCodeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderProductPromoCodeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
