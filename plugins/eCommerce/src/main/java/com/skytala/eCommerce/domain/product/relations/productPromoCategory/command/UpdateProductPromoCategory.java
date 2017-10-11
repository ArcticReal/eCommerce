package com.skytala.eCommerce.domain.product.relations.productPromoCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productPromoCategory.event.ProductPromoCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.productPromoCategory.model.ProductPromoCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoCategory extends Command {

private ProductPromoCategory elementToBeUpdated;

public UpdateProductPromoCategory(ProductPromoCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoCategory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoCategory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoCategory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoCategory.class);
}
success = false;
}
Event resultingEvent = new ProductPromoCategoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}