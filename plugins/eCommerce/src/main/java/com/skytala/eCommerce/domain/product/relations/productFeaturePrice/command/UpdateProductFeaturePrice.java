package com.skytala.eCommerce.domain.product.relations.productFeaturePrice.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.event.ProductFeaturePriceUpdated;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.model.ProductFeaturePrice;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeaturePrice extends Command {

private ProductFeaturePrice elementToBeUpdated;

public UpdateProductFeaturePrice(ProductFeaturePrice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeaturePrice getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeaturePrice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeaturePrice", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeaturePrice.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeaturePrice.class);
}
success = false;
}
Event resultingEvent = new ProductFeaturePriceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
