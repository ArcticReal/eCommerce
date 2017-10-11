package com.skytala.eCommerce.domain.party.relations.agreement.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.AgreementUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.Agreement;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreement extends Command {

private Agreement elementToBeUpdated;

public UpdateAgreement(Agreement elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Agreement getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Agreement elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Agreement", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Agreement.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Agreement.class);
}
success = false;
}
Event resultingEvent = new AgreementUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
