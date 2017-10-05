package com.skytala.eCommerce.domain.benefitType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.benefitType.event.BenefitTypeAdded;
import com.skytala.eCommerce.domain.benefitType.mapper.BenefitTypeMapper;
import com.skytala.eCommerce.domain.benefitType.model.BenefitType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBenefitType extends Command {

private BenefitType elementToBeAdded;
public AddBenefitType(BenefitType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BenefitType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBenefitTypeId(delegator.getNextSeqId("BenefitType"));
GenericValue newValue = delegator.makeValue("BenefitType", elementToBeAdded.mapAttributeField());
addedElement = BenefitTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BenefitTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
