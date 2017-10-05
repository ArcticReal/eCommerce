package com.skytala.eCommerce.domain.productType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.productType.event.ProductTypeUpdated;
import com.skytala.eCommerce.domain.productType.model.ProductType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductType extends Command {

private ProductType elementToBeUpdated;

public UpdateProductType(ProductType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductType.class);
}
success = false;
}
Event resultingEvent = new ProductTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
