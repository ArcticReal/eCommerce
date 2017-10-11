package com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.event.FacilityGroupRollupAdded;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.mapper.FacilityGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.model.FacilityGroupRollup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityGroupRollup extends Command {

private FacilityGroupRollup elementToBeAdded;
public AddFacilityGroupRollup(FacilityGroupRollup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityGroupRollup addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityGroupRollup", elementToBeAdded.mapAttributeField());
addedElement = FacilityGroupRollupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityGroupRollupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
