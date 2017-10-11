package com.skytala.eCommerce.domain.product.relations.productContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productContent.event.ProductContentUpdated;
import com.skytala.eCommerce.domain.product.relations.productContent.model.ProductContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductContent extends Command {

private ProductContent elementToBeUpdated;

public UpdateProductContent(ProductContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductContent.class);
}
success = false;
}
Event resultingEvent = new ProductContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
