package com.skytala.eCommerce.domain.party.relations.agreement.command.facilityAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl.AgreementFacilityApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.facilityAppl.AgreementFacilityAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementFacilityAppl extends Command {

private AgreementFacilityAppl elementToBeUpdated;

public UpdateAgreementFacilityAppl(AgreementFacilityAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementFacilityAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementFacilityAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementFacilityAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementFacilityAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementFacilityAppl.class);
}
success = false;
}
Event resultingEvent = new AgreementFacilityApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
