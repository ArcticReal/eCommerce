package com.skytala.eCommerce.domain.orderItemPriceInfo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderItemPriceInfo.event.OrderItemPriceInfoAdded;
import com.skytala.eCommerce.domain.orderItemPriceInfo.mapper.OrderItemPriceInfoMapper;
import com.skytala.eCommerce.domain.orderItemPriceInfo.model.OrderItemPriceInfo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemPriceInfo extends Command {

private OrderItemPriceInfo elementToBeAdded;
public AddOrderItemPriceInfo(OrderItemPriceInfo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemPriceInfo addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemPriceInfoId(delegator.getNextSeqId("OrderItemPriceInfo"));
GenericValue newValue = delegator.makeValue("OrderItemPriceInfo", elementToBeAdded.mapAttributeField());
addedElement = OrderItemPriceInfoMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemPriceInfoAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
