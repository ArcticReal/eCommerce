
package com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.event.SegmentGroupTypeFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.mapper.SegmentGroupTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.model.SegmentGroupType;


public class FindAllSegmentGroupTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SegmentGroupType> returnVal = new ArrayList<SegmentGroupType>();
try{
List<GenericValue> results = delegator.findAll("SegmentGroupType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SegmentGroupTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SegmentGroupTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
