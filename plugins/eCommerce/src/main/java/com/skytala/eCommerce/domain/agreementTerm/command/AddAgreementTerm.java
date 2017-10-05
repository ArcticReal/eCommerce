package com.skytala.eCommerce.domain.agreementTerm.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.agreementTerm.event.AgreementTermAdded;
import com.skytala.eCommerce.domain.agreementTerm.mapper.AgreementTermMapper;
import com.skytala.eCommerce.domain.agreementTerm.model.AgreementTerm;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementTerm extends Command {

private AgreementTerm elementToBeAdded;
public AddAgreementTerm(AgreementTerm elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementTerm addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementTermId(delegator.getNextSeqId("AgreementTerm"));
GenericValue newValue = delegator.makeValue("AgreementTerm", elementToBeAdded.mapAttributeField());
addedElement = AgreementTermMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementTermAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
