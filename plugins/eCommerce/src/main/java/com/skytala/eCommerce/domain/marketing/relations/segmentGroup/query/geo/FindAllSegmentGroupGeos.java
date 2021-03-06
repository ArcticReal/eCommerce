
package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.query.geo;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo.SegmentGroupGeoFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.geo.SegmentGroupGeoMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.geo.SegmentGroupGeo;


public class FindAllSegmentGroupGeos extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SegmentGroupGeo> returnVal = new ArrayList<SegmentGroupGeo>();
try{
List<GenericValue> results = delegator.findAll("SegmentGroupGeo", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SegmentGroupGeoMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SegmentGroupGeoFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
