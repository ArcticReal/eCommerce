package com.skytala.eCommerce.domain.product.relations.product.command.featureDataResource;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource.ProductFeatureDataResourceUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.featureDataResource.ProductFeatureDataResource;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureDataResource extends Command {

private ProductFeatureDataResource elementToBeUpdated;

public UpdateProductFeatureDataResource(ProductFeatureDataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureDataResource getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureDataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureDataResource", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureDataResource.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureDataResource.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureDataResourceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
