package com.skytala.eCommerce.domain.product.relations.costComponentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.costComponentType.event.CostComponentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponentType.model.CostComponentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCostComponentType extends Command {

private CostComponentType elementToBeUpdated;

public UpdateCostComponentType(CostComponentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CostComponentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CostComponentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CostComponentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CostComponentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CostComponentType.class);
}
success = false;
}
Event resultingEvent = new CostComponentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
