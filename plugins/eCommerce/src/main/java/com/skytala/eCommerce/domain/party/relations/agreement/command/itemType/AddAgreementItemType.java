package com.skytala.eCommerce.domain.party.relations.agreement.command.itemType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemType.AgreementItemTypeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.itemType.AgreementItemTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.itemType.AgreementItemType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementItemType extends Command {

private AgreementItemType elementToBeAdded;
public AddAgreementItemType(AgreementItemType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementItemType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemTypeId(delegator.getNextSeqId("AgreementItemType"));
GenericValue newValue = delegator.makeValue("AgreementItemType", elementToBeAdded.mapAttributeField());
addedElement = AgreementItemTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementItemTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
