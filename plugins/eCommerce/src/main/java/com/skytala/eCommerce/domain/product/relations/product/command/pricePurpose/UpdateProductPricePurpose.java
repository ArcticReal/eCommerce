package com.skytala.eCommerce.domain.product.relations.product.command.pricePurpose;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.pricePurpose.ProductPricePurposeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.pricePurpose.ProductPricePurpose;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPricePurpose extends Command {

private ProductPricePurpose elementToBeUpdated;

public UpdateProductPricePurpose(ProductPricePurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPricePurpose getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPricePurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPricePurpose", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPricePurpose.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPricePurpose.class);
}
success = false;
}
Event resultingEvent = new ProductPricePurposeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
