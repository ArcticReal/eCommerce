package com.skytala.eCommerce.domain.product.relations.costComponent.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute.CostComponentAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.attribute.CostComponentAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCostComponentAttribute extends Command {

private CostComponentAttribute elementToBeUpdated;

public UpdateCostComponentAttribute(CostComponentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CostComponentAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CostComponentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CostComponentAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CostComponentAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CostComponentAttribute.class);
}
success = false;
}
Event resultingEvent = new CostComponentAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
