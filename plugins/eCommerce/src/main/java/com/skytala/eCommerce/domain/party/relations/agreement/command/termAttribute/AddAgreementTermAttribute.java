package com.skytala.eCommerce.domain.party.relations.agreement.command.termAttribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.event.termAttribute.AgreementTermAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.termAttribute.AgreementTermAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.termAttribute.AgreementTermAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementTermAttribute extends Command {

private AgreementTermAttribute elementToBeAdded;
public AddAgreementTermAttribute(AgreementTermAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementTermAttribute addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("AgreementTermAttribute"));
GenericValue newValue = delegator.makeValue("AgreementTermAttribute", elementToBeAdded.mapAttributeField());
addedElement = AgreementTermAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementTermAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
