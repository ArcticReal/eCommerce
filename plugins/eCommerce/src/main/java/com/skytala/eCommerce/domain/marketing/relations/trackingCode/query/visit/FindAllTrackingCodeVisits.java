
package com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.visit;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit.TrackingCodeVisitFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.visit.TrackingCodeVisitMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.visit.TrackingCodeVisit;


public class FindAllTrackingCodeVisits extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrackingCodeVisit> returnVal = new ArrayList<TrackingCodeVisit>();
try{
List<GenericValue> results = delegator.findAll("TrackingCodeVisit", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TrackingCodeVisitMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TrackingCodeVisitFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
