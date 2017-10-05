package com.skytala.eCommerce.domain.varianceReason.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.varianceReason.event.VarianceReasonUpdated;
import com.skytala.eCommerce.domain.varianceReason.model.VarianceReason;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateVarianceReason extends Command {

private VarianceReason elementToBeUpdated;

public UpdateVarianceReason(VarianceReason elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public VarianceReason getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(VarianceReason elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("VarianceReason", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(VarianceReason.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(VarianceReason.class);
}
success = false;
}
Event resultingEvent = new VarianceReasonUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
