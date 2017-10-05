package com.skytala.eCommerce.domain.productContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.productContentType.event.ProductContentTypeUpdated;
import com.skytala.eCommerce.domain.productContentType.model.ProductContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductContentType extends Command {

private ProductContentType elementToBeUpdated;

public UpdateProductContentType(ProductContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductContentType.class);
}
success = false;
}
Event resultingEvent = new ProductContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
