package com.skytala.eCommerce.domain.product.relations.product.command.meterType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.meterType.ProductMeterTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.meterType.ProductMeterType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductMeterType extends Command {

private ProductMeterType elementToBeUpdated;

public UpdateProductMeterType(ProductMeterType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductMeterType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductMeterType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductMeterType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductMeterType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductMeterType.class);
}
success = false;
}
Event resultingEvent = new ProductMeterTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
