package com.skytala.eCommerce.domain.party.relations.agreement.command.promoAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl.AgreementPromoApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.promoAppl.AgreementPromoAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementPromoAppl extends Command {

private AgreementPromoAppl elementToBeUpdated;

public UpdateAgreementPromoAppl(AgreementPromoAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementPromoAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementPromoAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementPromoAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementPromoAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementPromoAppl.class);
}
success = false;
}
Event resultingEvent = new AgreementPromoApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
