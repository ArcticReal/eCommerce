package com.skytala.eCommerce.domain.party.relations.agreementContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementContent.event.AgreementContentAdded;
import com.skytala.eCommerce.domain.party.relations.agreementContent.mapper.AgreementContentMapper;
import com.skytala.eCommerce.domain.party.relations.agreementContent.model.AgreementContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementContent extends Command {

private AgreementContent elementToBeAdded;
public AddAgreementContent(AgreementContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementContent addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemSeqId(delegator.getNextSeqId("AgreementContent"));
GenericValue newValue = delegator.makeValue("AgreementContent", elementToBeAdded.mapAttributeField());
addedElement = AgreementContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
