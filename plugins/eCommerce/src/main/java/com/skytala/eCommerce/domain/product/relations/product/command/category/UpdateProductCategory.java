package com.skytala.eCommerce.domain.product.relations.product.command.category;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCategory extends Command {

private ProductCategory elementToBeUpdated;

public UpdateProductCategory(ProductCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCategory.class);
}
success = false;
}
Event resultingEvent = new ProductCategoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
