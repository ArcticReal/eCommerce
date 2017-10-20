package com.skytala.eCommerce.domain.product.relations.product.command.priceType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.priceType.ProductPriceTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.priceType.ProductPriceType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPriceType extends Command {

private ProductPriceType elementToBeUpdated;

public UpdateProductPriceType(ProductPriceType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPriceType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPriceType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPriceType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPriceType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPriceType.class);
}
success = false;
}
Event resultingEvent = new ProductPriceTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
