package com.skytala.eCommerce.domain.product.relations.facilityAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.event.FacilityAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.mapper.FacilityAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.model.FacilityAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityAttribute extends Command {

private FacilityAttribute elementToBeAdded;
public AddFacilityAttribute(FacilityAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityAttribute", elementToBeAdded.mapAttributeField());
addedElement = FacilityAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}