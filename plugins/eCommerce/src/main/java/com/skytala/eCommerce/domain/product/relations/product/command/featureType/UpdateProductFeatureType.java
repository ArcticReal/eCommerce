package com.skytala.eCommerce.domain.product.relations.product.command.featureType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.featureType.ProductFeatureTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.featureType.ProductFeatureType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureType extends Command {

private ProductFeatureType elementToBeUpdated;

public UpdateProductFeatureType(ProductFeatureType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureType.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
