
package com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.orderReturn;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.orderReturn.TrackingCodeOrderReturnFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.orderReturn.TrackingCodeOrderReturnMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.orderReturn.TrackingCodeOrderReturn;


public class FindAllTrackingCodeOrderReturns extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrackingCodeOrderReturn> returnVal = new ArrayList<TrackingCodeOrderReturn>();
try{
List<GenericValue> results = delegator.findAll("TrackingCodeOrderReturn", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TrackingCodeOrderReturnMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TrackingCodeOrderReturnFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
