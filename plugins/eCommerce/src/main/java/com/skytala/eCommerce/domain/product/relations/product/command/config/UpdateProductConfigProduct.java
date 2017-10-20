package com.skytala.eCommerce.domain.product.relations.product.command.config;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigProductUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfigProduct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductConfigProduct extends Command {

private ProductConfigProduct elementToBeUpdated;

public UpdateProductConfigProduct(ProductConfigProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductConfigProduct getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductConfigProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductConfigProduct", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductConfigProduct.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductConfigProduct.class);
}
success = false;
}
Event resultingEvent = new ProductConfigProductUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
