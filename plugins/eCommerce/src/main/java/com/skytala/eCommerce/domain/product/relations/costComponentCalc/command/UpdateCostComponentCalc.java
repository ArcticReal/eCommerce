package com.skytala.eCommerce.domain.product.relations.costComponentCalc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.costComponentCalc.event.CostComponentCalcUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponentCalc.model.CostComponentCalc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCostComponentCalc extends Command {

private CostComponentCalc elementToBeUpdated;

public UpdateCostComponentCalc(CostComponentCalc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CostComponentCalc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CostComponentCalc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CostComponentCalc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CostComponentCalc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CostComponentCalc.class);
}
success = false;
}
Event resultingEvent = new CostComponentCalcUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
