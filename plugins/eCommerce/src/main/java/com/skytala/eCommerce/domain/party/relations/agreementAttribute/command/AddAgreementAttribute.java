package com.skytala.eCommerce.domain.party.relations.agreementAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementAttribute.event.AgreementAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.agreementAttribute.mapper.AgreementAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreementAttribute.model.AgreementAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementAttribute extends Command {

private AgreementAttribute elementToBeAdded;
public AddAgreementAttribute(AgreementAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("AgreementAttribute", elementToBeAdded.mapAttributeField());
addedElement = AgreementAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}