package com.skytala.eCommerce.domain.product.relations.facility.command.contactMech;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMech.FacilityContactMechAdded;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.contactMech.FacilityContactMechMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.contactMech.FacilityContactMech;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityContactMech extends Command {

private FacilityContactMech elementToBeAdded;
public AddFacilityContactMech(FacilityContactMech elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityContactMech addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityContactMech", elementToBeAdded.mapAttributeField());
addedElement = FacilityContactMechMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityContactMechAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
