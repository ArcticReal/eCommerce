package com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.event.AgreementItemAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.mapper.AgreementItemAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.model.AgreementItemAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementItemAttribute extends Command {

private AgreementItemAttribute elementToBeAdded;
public AddAgreementItemAttribute(AgreementItemAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementItemAttribute addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemSeqId(delegator.getNextSeqId("AgreementItemAttribute"));
GenericValue newValue = delegator.makeValue("AgreementItemAttribute", elementToBeAdded.mapAttributeField());
addedElement = AgreementItemAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementItemAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
