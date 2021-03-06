package com.skytala.eCommerce.domain.party.relations.agreement.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr.AgreementTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.typeAttr.AgreementTypeAttr;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementTypeAttr extends Command {

private AgreementTypeAttr elementToBeUpdated;

public UpdateAgreementTypeAttr(AgreementTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementTypeAttr getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementTypeAttr", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementTypeAttr.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementTypeAttr.class);
}
success = false;
}
Event resultingEvent = new AgreementTypeAttrUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
