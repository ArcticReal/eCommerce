package com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.event.AgreementEmploymentApplAdded;
import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.mapper.AgreementEmploymentApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.model.AgreementEmploymentAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementEmploymentAppl extends Command {

private AgreementEmploymentAppl elementToBeAdded;
public AddAgreementEmploymentAppl(AgreementEmploymentAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementEmploymentAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("AgreementEmploymentAppl", elementToBeAdded.mapAttributeField());
addedElement = AgreementEmploymentApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementEmploymentApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
