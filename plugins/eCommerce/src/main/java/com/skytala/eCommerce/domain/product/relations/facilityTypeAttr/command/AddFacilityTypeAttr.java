package com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.event.FacilityTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.mapper.FacilityTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.model.FacilityTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityTypeAttr extends Command {

private FacilityTypeAttr elementToBeAdded;
public AddFacilityTypeAttr(FacilityTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = FacilityTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
