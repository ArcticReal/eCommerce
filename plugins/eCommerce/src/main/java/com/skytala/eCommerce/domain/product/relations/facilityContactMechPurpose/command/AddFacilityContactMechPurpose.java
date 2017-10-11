package com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.event.FacilityContactMechPurposeAdded;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.mapper.FacilityContactMechPurposeMapper;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.model.FacilityContactMechPurpose;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityContactMechPurpose extends Command {

private FacilityContactMechPurpose elementToBeAdded;
public AddFacilityContactMechPurpose(FacilityContactMechPurpose elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityContactMechPurpose addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityContactMechPurpose", elementToBeAdded.mapAttributeField());
addedElement = FacilityContactMechPurposeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityContactMechPurposeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
