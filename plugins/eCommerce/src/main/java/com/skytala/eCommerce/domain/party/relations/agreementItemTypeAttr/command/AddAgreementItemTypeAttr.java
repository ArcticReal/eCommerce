package com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.event.AgreementItemTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.mapper.AgreementItemTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.model.AgreementItemTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementItemTypeAttr extends Command {

private AgreementItemTypeAttr elementToBeAdded;
public AddAgreementItemTypeAttr(AgreementItemTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementItemTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("AgreementItemTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = AgreementItemTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementItemTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
