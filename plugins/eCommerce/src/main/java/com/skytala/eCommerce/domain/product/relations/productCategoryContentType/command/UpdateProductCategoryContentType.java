package com.skytala.eCommerce.domain.product.relations.productCategoryContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productCategoryContentType.event.ProductCategoryContentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryContentType.model.ProductCategoryContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCategoryContentType extends Command {

private ProductCategoryContentType elementToBeUpdated;

public UpdateProductCategoryContentType(ProductCategoryContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategoryContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategoryContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategoryContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategoryContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCategoryContentType.class);
}
success = false;
}
Event resultingEvent = new ProductCategoryContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
