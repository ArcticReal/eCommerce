package com.skytala.eCommerce.domain.product.relations.productMeter.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productMeter.event.ProductMeterUpdated;
import com.skytala.eCommerce.domain.product.relations.productMeter.model.ProductMeter;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductMeter extends Command {

private ProductMeter elementToBeUpdated;

public UpdateProductMeter(ProductMeter elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductMeter getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductMeter elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductMeter", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductMeter.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductMeter.class);
}
success = false;
}
Event resultingEvent = new ProductMeterUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
