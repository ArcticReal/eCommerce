package com.skytala.eCommerce.domain.product.relations.costComponent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.CostComponentUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.CostComponent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCostComponent extends Command {

private CostComponent elementToBeUpdated;

public UpdateCostComponent(CostComponent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CostComponent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CostComponent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CostComponent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CostComponent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CostComponent.class);
}
success = false;
}
Event resultingEvent = new CostComponentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
