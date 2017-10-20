package com.skytala.eCommerce.domain.party.relations.agreement.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr.AgreementTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.typeAttr.AgreementTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.typeAttr.AgreementTypeAttr;
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
