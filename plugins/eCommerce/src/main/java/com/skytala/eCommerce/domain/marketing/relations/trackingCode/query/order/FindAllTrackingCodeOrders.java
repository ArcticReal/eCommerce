
package com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.order;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.order.TrackingCodeOrderFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.order.TrackingCodeOrderMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.order.TrackingCodeOrder;


public class FindAllTrackingCodeOrders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrackingCodeOrder> returnVal = new ArrayList<TrackingCodeOrder>();
try{
List<GenericValue> results = delegator.findAll("TrackingCodeOrder", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TrackingCodeOrderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TrackingCodeOrderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
