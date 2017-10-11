package com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.event.AgreementTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.mapper.AgreementTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.model.AgreementTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementTypeAttr extends Command {

private AgreementTypeAttr elementToBeAdded;
public AddAgreementTypeAttr(AgreementTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("AgreementTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = AgreementTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
