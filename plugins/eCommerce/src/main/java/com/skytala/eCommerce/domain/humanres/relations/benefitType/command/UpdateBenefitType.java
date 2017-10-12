package com.skytala.eCommerce.domain.humanres.relations.benefitType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.event.BenefitTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.model.BenefitType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBenefitType extends Command {

private BenefitType elementToBeUpdated;

public UpdateBenefitType(BenefitType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BenefitType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BenefitType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BenefitType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BenefitType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BenefitType.class);
}
success = false;
}
Event resultingEvent = new BenefitTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
