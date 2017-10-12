
package com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.event.SegmentGroupClassificationFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.mapper.SegmentGroupClassificationMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.model.SegmentGroupClassification;


public class FindAllSegmentGroupClassifications extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SegmentGroupClassification> returnVal = new ArrayList<SegmentGroupClassification>();
try{
List<GenericValue> results = delegator.findAll("SegmentGroupClassification", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SegmentGroupClassificationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SegmentGroupClassificationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
