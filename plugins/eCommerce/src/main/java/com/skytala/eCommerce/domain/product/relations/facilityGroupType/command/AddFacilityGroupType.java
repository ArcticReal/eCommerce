package com.skytala.eCommerce.domain.product.relations.facilityGroupType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroupType.event.FacilityGroupTypeAdded;
import com.skytala.eCommerce.domain.product.relations.facilityGroupType.mapper.FacilityGroupTypeMapper;
import com.skytala.eCommerce.domain.product.relations.facilityGroupType.model.FacilityGroupType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityGroupType extends Command {

private FacilityGroupType elementToBeAdded;
public AddFacilityGroupType(FacilityGroupType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityGroupType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFacilityGroupTypeId(delegator.getNextSeqId("FacilityGroupType"));
GenericValue newValue = delegator.makeValue("FacilityGroupType", elementToBeAdded.mapAttributeField());
addedElement = FacilityGroupTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityGroupTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
