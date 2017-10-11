package com.skytala.eCommerce.domain.product.relations.productPromoUse.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productPromoUse.event.ProductPromoUseUpdated;
import com.skytala.eCommerce.domain.product.relations.productPromoUse.model.ProductPromoUse;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoUse extends Command {

private ProductPromoUse elementToBeUpdated;

public UpdateProductPromoUse(ProductPromoUse elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoUse getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoUse elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoUse", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoUse.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoUse.class);
}
success = false;
}
Event resultingEvent = new ProductPromoUseUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
