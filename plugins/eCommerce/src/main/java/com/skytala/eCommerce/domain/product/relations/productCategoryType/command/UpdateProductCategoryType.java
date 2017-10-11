package com.skytala.eCommerce.domain.product.relations.productCategoryType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productCategoryType.event.ProductCategoryTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryType.model.ProductCategoryType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCategoryType extends Command {

private ProductCategoryType elementToBeUpdated;

public UpdateProductCategoryType(ProductCategoryType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategoryType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategoryType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategoryType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategoryType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCategoryType.class);
}
success = false;
}
Event resultingEvent = new ProductCategoryTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
