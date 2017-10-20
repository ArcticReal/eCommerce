package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.classification;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.classification.SegmentGroupClassificationAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.classification.SegmentGroupClassificationMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.classification.SegmentGroupClassification;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSegmentGroupClassification extends Command {

private SegmentGroupClassification elementToBeAdded;
public AddSegmentGroupClassification(SegmentGroupClassification elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SegmentGroupClassification addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SegmentGroupClassification", elementToBeAdded.mapAttributeField());
addedElement = SegmentGroupClassificationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SegmentGroupClassificationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
