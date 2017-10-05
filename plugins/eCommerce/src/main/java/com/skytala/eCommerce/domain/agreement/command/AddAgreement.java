package com.skytala.eCommerce.domain.agreement.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.agreement.event.AgreementAdded;
import com.skytala.eCommerce.domain.agreement.mapper.AgreementMapper;
import com.skytala.eCommerce.domain.agreement.model.Agreement;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreement extends Command {

private Agreement elementToBeAdded;
public AddAgreement(Agreement elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Agreement addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementId(delegator.getNextSeqId("Agreement"));
GenericValue newValue = delegator.makeValue("Agreement", elementToBeAdded.mapAttributeField());
addedElement = AgreementMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
