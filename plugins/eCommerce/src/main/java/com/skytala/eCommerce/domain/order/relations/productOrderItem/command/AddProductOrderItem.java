package com.skytala.eCommerce.domain.order.relations.productOrderItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.productOrderItem.event.ProductOrderItemAdded;
import com.skytala.eCommerce.domain.order.relations.productOrderItem.mapper.ProductOrderItemMapper;
import com.skytala.eCommerce.domain.order.relations.productOrderItem.model.ProductOrderItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductOrderItem extends Command {

private ProductOrderItem elementToBeAdded;
public AddProductOrderItem(ProductOrderItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductOrderItem addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductOrderItem", elementToBeAdded.mapAttributeField());
addedElement = ProductOrderItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductOrderItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
