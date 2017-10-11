package com.skytala.eCommerce.domain.product.relations.productCategoryRollup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productCategoryRollup.event.ProductCategoryRollupUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryRollup.model.ProductCategoryRollup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCategoryRollup extends Command {

private ProductCategoryRollup elementToBeUpdated;

public UpdateProductCategoryRollup(ProductCategoryRollup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategoryRollup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategoryRollup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategoryRollup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategoryRollup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCategoryRollup.class);
}
success = false;
}
Event resultingEvent = new ProductCategoryRollupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
