package com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.event.AgreementFacilityApplAdded;
import com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.mapper.AgreementFacilityApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.model.AgreementFacilityAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementFacilityAppl extends Command {

private AgreementFacilityAppl elementToBeAdded;
public AddAgreementFacilityAppl(AgreementFacilityAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementFacilityAppl addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemSeqId(delegator.getNextSeqId("AgreementFacilityAppl"));
GenericValue newValue = delegator.makeValue("AgreementFacilityAppl", elementToBeAdded.mapAttributeField());
addedElement = AgreementFacilityApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementFacilityApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
