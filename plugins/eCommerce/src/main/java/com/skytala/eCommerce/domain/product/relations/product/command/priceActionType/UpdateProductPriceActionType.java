package com.skytala.eCommerce.domain.product.relations.product.command.priceActionType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.priceActionType.ProductPriceActionType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPriceActionType extends Command {

private ProductPriceActionType elementToBeUpdated;

public UpdateProductPriceActionType(ProductPriceActionType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPriceActionType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPriceActionType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPriceActionType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPriceActionType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPriceActionType.class);
}
success = false;
}
Event resultingEvent = new ProductPriceActionTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
