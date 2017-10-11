package com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.event.AgreementEmploymentApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.model.AgreementEmploymentAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementEmploymentAppl extends Command {

private AgreementEmploymentAppl elementToBeUpdated;

public UpdateAgreementEmploymentAppl(AgreementEmploymentAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementEmploymentAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementEmploymentAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementEmploymentAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementEmploymentAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementEmploymentAppl.class);
}
success = false;
}
Event resultingEvent = new AgreementEmploymentApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
