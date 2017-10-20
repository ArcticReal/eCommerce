package com.skytala.eCommerce.domain.party.relations.agreement.command.geographicalApplic;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic.AgreementGeographicalApplicUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.geographicalApplic.AgreementGeographicalApplic;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementGeographicalApplic extends Command {

private AgreementGeographicalApplic elementToBeUpdated;

public UpdateAgreementGeographicalApplic(AgreementGeographicalApplic elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementGeographicalApplic getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementGeographicalApplic elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementGeographicalApplic", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementGeographicalApplic.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementGeographicalApplic.class);
}
success = false;
}
Event resultingEvent = new AgreementGeographicalApplicUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
