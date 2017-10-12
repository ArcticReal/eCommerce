package com.skytala.eCommerce.domain.accounting.relations.rateType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.rateType.model.RateType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRateType extends Command {

private RateType elementToBeUpdated;

public UpdateRateType(RateType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RateType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RateType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RateType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RateType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RateType.class);
}
success = false;
}
Event resultingEvent = new RateTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
