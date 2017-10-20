package com.skytala.eCommerce.domain.party.relations.agreement.command.workEffortApplic;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic.AgreementWorkEffortApplicUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.workEffortApplic.AgreementWorkEffortApplic;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementWorkEffortApplic extends Command {

private AgreementWorkEffortApplic elementToBeUpdated;

public UpdateAgreementWorkEffortApplic(AgreementWorkEffortApplic elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementWorkEffortApplic getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementWorkEffortApplic elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementWorkEffortApplic", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementWorkEffortApplic.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementWorkEffortApplic.class);
}
success = false;
}
Event resultingEvent = new AgreementWorkEffortApplicUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
