package com.skytala.eCommerce.domain.product.relations.productPriceCond.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.event.ProductPriceCondUpdated;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.model.ProductPriceCond;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPriceCond extends Command {

private ProductPriceCond elementToBeUpdated;

public UpdateProductPriceCond(ProductPriceCond elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPriceCond getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPriceCond elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPriceCond", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPriceCond.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPriceCond.class);
}
success = false;
}
Event resultingEvent = new ProductPriceCondUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
