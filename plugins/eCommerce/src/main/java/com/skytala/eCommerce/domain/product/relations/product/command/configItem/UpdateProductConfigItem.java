package com.skytala.eCommerce.domain.product.relations.product.command.configItem;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.configItem.ProductConfigItemUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.configItem.ProductConfigItem;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductConfigItem extends Command {

private ProductConfigItem elementToBeUpdated;

public UpdateProductConfigItem(ProductConfigItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductConfigItem getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductConfigItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductConfigItem", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductConfigItem.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductConfigItem.class);
}
success = false;
}
Event resultingEvent = new ProductConfigItemUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
