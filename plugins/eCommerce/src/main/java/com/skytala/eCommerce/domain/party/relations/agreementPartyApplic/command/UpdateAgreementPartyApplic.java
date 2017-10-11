package com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.event.AgreementPartyApplicUpdated;
import com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.model.AgreementPartyApplic;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementPartyApplic extends Command {

private AgreementPartyApplic elementToBeUpdated;

public UpdateAgreementPartyApplic(AgreementPartyApplic elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementPartyApplic getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementPartyApplic elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementPartyApplic", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementPartyApplic.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementPartyApplic.class);
}
success = false;
}
Event resultingEvent = new AgreementPartyApplicUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}