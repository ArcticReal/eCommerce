package com.skytala.eCommerce.domain.product.relations.product.command.configConfig;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.configConfig.ProductConfigConfigUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.configConfig.ProductConfigConfig;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductConfigConfig extends Command {

private ProductConfigConfig elementToBeUpdated;

public UpdateProductConfigConfig(ProductConfigConfig elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductConfigConfig getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductConfigConfig elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductConfigConfig", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductConfigConfig.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductConfigConfig.class);
}
success = false;
}
Event resultingEvent = new ProductConfigConfigUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
