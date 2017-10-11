package com.skytala.eCommerce.domain.product.relations.productCategoryLink.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.event.ProductCategoryLinkUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.model.ProductCategoryLink;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCategoryLink extends Command {

private ProductCategoryLink elementToBeUpdated;

public UpdateProductCategoryLink(ProductCategoryLink elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategoryLink getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategoryLink elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategoryLink", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategoryLink.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCategoryLink.class);
}
success = false;
}
Event resultingEvent = new ProductCategoryLinkUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
