package com.skytala.eCommerce.domain.party.relations.agreementItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementItem.event.AgreementItemAdded;
import com.skytala.eCommerce.domain.party.relations.agreementItem.mapper.AgreementItemMapper;
import com.skytala.eCommerce.domain.party.relations.agreementItem.model.AgreementItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementItem extends Command {

private AgreementItem elementToBeAdded;
public AddAgreementItem(AgreementItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemSeqId(delegator.getNextSeqId("AgreementItem"));
GenericValue newValue = delegator.makeValue("AgreementItem", elementToBeAdded.mapAttributeField());
addedElement = AgreementItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
