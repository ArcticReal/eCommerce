
package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.SegmentGroupFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.SegmentGroupMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.SegmentGroup;


public class FindAllSegmentGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SegmentGroup> returnVal = new ArrayList<SegmentGroup>();
try{
List<GenericValue> results = delegator.findAll("SegmentGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SegmentGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SegmentGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
