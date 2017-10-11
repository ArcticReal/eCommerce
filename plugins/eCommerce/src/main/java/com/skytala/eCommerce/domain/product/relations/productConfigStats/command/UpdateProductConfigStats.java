package com.skytala.eCommerce.domain.product.relations.productConfigStats.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productConfigStats.event.ProductConfigStatsUpdated;
import com.skytala.eCommerce.domain.product.relations.productConfigStats.model.ProductConfigStats;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductConfigStats extends Command {

private ProductConfigStats elementToBeUpdated;

public UpdateProductConfigStats(ProductConfigStats elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductConfigStats getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductConfigStats elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductConfigStats", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductConfigStats.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductConfigStats.class);
}
success = false;
}
Event resultingEvent = new ProductConfigStatsUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
