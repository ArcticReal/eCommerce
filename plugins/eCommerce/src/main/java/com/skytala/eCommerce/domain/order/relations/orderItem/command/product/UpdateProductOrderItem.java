package com.skytala.eCommerce.domain.order.relations.orderItem.command.product;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.product.ProductOrderItemUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.product.ProductOrderItem;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductOrderItem extends Command {

private ProductOrderItem elementToBeUpdated;

public UpdateProductOrderItem(ProductOrderItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductOrderItem getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductOrderItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductOrderItem", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductOrderItem.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductOrderItem.class);
}
success = false;
}
Event resultingEvent = new ProductOrderItemUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
