package com.skytala.eCommerce.domain.product.relations.product.command.featureCategory;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategory.ProductFeatureCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.featureCategory.ProductFeatureCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureCategory extends Command {

private ProductFeatureCategory elementToBeUpdated;

public UpdateProductFeatureCategory(ProductFeatureCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureCategory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureCategory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureCategory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureCategory.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureCategoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
