package com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.event.VarianceReasonGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.model.VarianceReasonGlAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateVarianceReasonGlAccount extends Command {

private VarianceReasonGlAccount elementToBeUpdated;

public UpdateVarianceReasonGlAccount(VarianceReasonGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public VarianceReasonGlAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(VarianceReasonGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("VarianceReasonGlAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(VarianceReasonGlAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(VarianceReasonGlAccount.class);
}
success = false;
}
Event resultingEvent = new VarianceReasonGlAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
