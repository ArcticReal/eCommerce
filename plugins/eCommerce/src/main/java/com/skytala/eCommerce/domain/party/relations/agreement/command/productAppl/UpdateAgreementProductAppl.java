package com.skytala.eCommerce.domain.party.relations.agreement.command.productAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl.AgreementProductApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.productAppl.AgreementProductAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementProductAppl extends Command {

private AgreementProductAppl elementToBeUpdated;

public UpdateAgreementProductAppl(AgreementProductAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementProductAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementProductAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementProductAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementProductAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementProductAppl.class);
}
success = false;
}
Event resultingEvent = new AgreementProductApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
