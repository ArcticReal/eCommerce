package com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.event.ProductConfigOptionIactnUpdated;
import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.model.ProductConfigOptionIactn;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductConfigOptionIactn extends Command {

private ProductConfigOptionIactn elementToBeUpdated;

public UpdateProductConfigOptionIactn(ProductConfigOptionIactn elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductConfigOptionIactn getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductConfigOptionIactn elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductConfigOptionIactn", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductConfigOptionIactn.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductConfigOptionIactn.class);
}
success = false;
}
Event resultingEvent = new ProductConfigOptionIactnUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
