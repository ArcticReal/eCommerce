package com.skytala.eCommerce.domain.agreementType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.agreementType.event.AgreementTypeAdded;
import com.skytala.eCommerce.domain.agreementType.mapper.AgreementTypeMapper;
import com.skytala.eCommerce.domain.agreementType.model.AgreementType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementType extends Command {

private AgreementType elementToBeAdded;
public AddAgreementType(AgreementType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementTypeId(delegator.getNextSeqId("AgreementType"));
GenericValue newValue = delegator.makeValue("AgreementType", elementToBeAdded.mapAttributeField());
addedElement = AgreementTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
