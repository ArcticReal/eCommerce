package com.skytala.eCommerce.domain.product.relations.productPromoCode.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productPromoCode.event.ProductPromoCodeUpdated;
import com.skytala.eCommerce.domain.product.relations.productPromoCode.model.ProductPromoCode;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoCode extends Command {

private ProductPromoCode elementToBeUpdated;

public UpdateProductPromoCode(ProductPromoCode elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoCode getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoCode elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoCode", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoCode.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoCode.class);
}
success = false;
}
Event resultingEvent = new ProductPromoCodeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
