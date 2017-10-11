package com.skytala.eCommerce.domain.party.relations.agreementContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.event.AgreementContentTypeAdded;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.mapper.AgreementContentTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.model.AgreementContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementContentType extends Command {

private AgreementContentType elementToBeAdded;
public AddAgreementContentType(AgreementContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementContentTypeId(delegator.getNextSeqId("AgreementContentType"));
GenericValue newValue = delegator.makeValue("AgreementContentType", elementToBeAdded.mapAttributeField());
addedElement = AgreementContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
