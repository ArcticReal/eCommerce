package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.geo;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo.SegmentGroupGeoUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.geo.SegmentGroupGeo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSegmentGroupGeo extends Command {

private SegmentGroupGeo elementToBeUpdated;

public UpdateSegmentGroupGeo(SegmentGroupGeo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SegmentGroupGeo getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SegmentGroupGeo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SegmentGroupGeo", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SegmentGroupGeo.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SegmentGroupGeo.class);
}
success = false;
}
Event resultingEvent = new SegmentGroupGeoUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
