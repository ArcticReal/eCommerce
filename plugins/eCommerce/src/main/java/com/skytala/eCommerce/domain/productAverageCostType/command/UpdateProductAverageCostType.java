package com.skytala.eCommerce.domain.productAverageCostType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.productAverageCostType.event.ProductAverageCostTypeUpdated;
import com.skytala.eCommerce.domain.productAverageCostType.model.ProductAverageCostType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductAverageCostType extends Command {

private ProductAverageCostType elementToBeUpdated;

public UpdateProductAverageCostType(ProductAverageCostType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductAverageCostType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductAverageCostType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductAverageCostType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductAverageCostType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductAverageCostType.class);
}
success = false;
}
Event resultingEvent = new ProductAverageCostTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
