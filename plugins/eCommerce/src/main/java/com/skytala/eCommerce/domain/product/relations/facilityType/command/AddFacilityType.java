package com.skytala.eCommerce.domain.product.relations.facilityType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityType.event.FacilityTypeAdded;
import com.skytala.eCommerce.domain.product.relations.facilityType.mapper.FacilityTypeMapper;
import com.skytala.eCommerce.domain.product.relations.facilityType.model.FacilityType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityType extends Command {

private FacilityType elementToBeAdded;
public AddFacilityType(FacilityType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFacilityTypeId(delegator.getNextSeqId("FacilityType"));
GenericValue newValue = delegator.makeValue("FacilityType", elementToBeAdded.mapAttributeField());
addedElement = FacilityTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
