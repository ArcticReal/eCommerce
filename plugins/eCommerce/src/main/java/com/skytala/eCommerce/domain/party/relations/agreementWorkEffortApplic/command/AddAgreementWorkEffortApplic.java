package com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.event.AgreementWorkEffortApplicAdded;
import com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.mapper.AgreementWorkEffortApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.model.AgreementWorkEffortApplic;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementWorkEffortApplic extends Command {

private AgreementWorkEffortApplic elementToBeAdded;
public AddAgreementWorkEffortApplic(AgreementWorkEffortApplic elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementWorkEffortApplic addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemSeqId(delegator.getNextSeqId("AgreementWorkEffortApplic"));
GenericValue newValue = delegator.makeValue("AgreementWorkEffortApplic", elementToBeAdded.mapAttributeField());
addedElement = AgreementWorkEffortApplicMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementWorkEffortApplicAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
