package com.skytala.eCommerce.domain.product.relations.productPriceAction.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.event.ProductPriceActionUpdated;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.model.ProductPriceAction;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPriceAction extends Command {

private ProductPriceAction elementToBeUpdated;

public UpdateProductPriceAction(ProductPriceAction elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPriceAction getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPriceAction elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPriceAction", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPriceAction.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPriceAction.class);
}
success = false;
}
Event resultingEvent = new ProductPriceActionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
