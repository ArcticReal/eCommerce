package com.skytala.eCommerce.domain.product.relations.productPriceChange.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productPriceChange.event.ProductPriceChangeUpdated;
import com.skytala.eCommerce.domain.product.relations.productPriceChange.model.ProductPriceChange;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPriceChange extends Command {

private ProductPriceChange elementToBeUpdated;

public UpdateProductPriceChange(ProductPriceChange elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPriceChange getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPriceChange elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPriceChange", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPriceChange.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPriceChange.class);
}
success = false;
}
Event resultingEvent = new ProductPriceChangeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
