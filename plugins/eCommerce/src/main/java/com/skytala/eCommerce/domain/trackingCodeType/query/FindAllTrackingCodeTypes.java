
package com.skytala.eCommerce.domain.trackingCodeType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.trackingCodeType.event.TrackingCodeTypeFound;
import com.skytala.eCommerce.domain.trackingCodeType.mapper.TrackingCodeTypeMapper;
import com.skytala.eCommerce.domain.trackingCodeType.model.TrackingCodeType;


public class FindAllTrackingCodeTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrackingCodeType> returnVal = new ArrayList<TrackingCodeType>();
try{
List<GenericValue> results = delegator.findAll("TrackingCodeType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TrackingCodeTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TrackingCodeTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
