package com.skytala.eCommerce.domain.product.relations.facility.command.content;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.event.content.FacilityContentAdded;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.content.FacilityContentMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.content.FacilityContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityContent extends Command {

private FacilityContent elementToBeAdded;
public AddFacilityContent(FacilityContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityContent", elementToBeAdded.mapAttributeField());
addedElement = FacilityContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
