package com.skytala.eCommerce.domain.product.relations.product.command.categoryGlAccount;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryGlAccount.ProductCategoryGlAccountUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryGlAccount.ProductCategoryGlAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCategoryGlAccount extends Command {

private ProductCategoryGlAccount elementToBeUpdated;

public UpdateProductCategoryGlAccount(ProductCategoryGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategoryGlAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategoryGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategoryGlAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategoryGlAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCategoryGlAccount.class);
}
success = false;
}
Event resultingEvent = new ProductCategoryGlAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
