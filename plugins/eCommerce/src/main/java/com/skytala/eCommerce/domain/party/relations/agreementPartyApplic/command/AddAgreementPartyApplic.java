package com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.event.AgreementPartyApplicAdded;
import com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.mapper.AgreementPartyApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.model.AgreementPartyApplic;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementPartyApplic extends Command {

private AgreementPartyApplic elementToBeAdded;
public AddAgreementPartyApplic(AgreementPartyApplic elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementPartyApplic addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemSeqId(delegator.getNextSeqId("AgreementPartyApplic"));
GenericValue newValue = delegator.makeValue("AgreementPartyApplic", elementToBeAdded.mapAttributeField());
addedElement = AgreementPartyApplicMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementPartyApplicAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}