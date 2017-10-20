package com.skytala.eCommerce.domain.party.relations.agreement.command.geographicalApplic;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic.AgreementGeographicalApplicAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.geographicalApplic.AgreementGeographicalApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.geographicalApplic.AgreementGeographicalApplic;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementGeographicalApplic extends Command {

private AgreementGeographicalApplic elementToBeAdded;
public AddAgreementGeographicalApplic(AgreementGeographicalApplic elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementGeographicalApplic addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemSeqId(delegator.getNextSeqId("AgreementGeographicalApplic"));
GenericValue newValue = delegator.makeValue("AgreementGeographicalApplic", elementToBeAdded.mapAttributeField());
addedElement = AgreementGeographicalApplicMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementGeographicalApplicAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
