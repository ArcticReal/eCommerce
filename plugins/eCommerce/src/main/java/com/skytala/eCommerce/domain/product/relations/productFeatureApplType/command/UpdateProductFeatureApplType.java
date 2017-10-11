package com.skytala.eCommerce.domain.product.relations.productFeatureApplType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplType.event.ProductFeatureApplTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplType.model.ProductFeatureApplType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureApplType extends Command {

private ProductFeatureApplType elementToBeUpdated;

public UpdateProductFeatureApplType(ProductFeatureApplType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureApplType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureApplType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureApplType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureApplType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureApplType.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureApplTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
