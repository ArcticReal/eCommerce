
package com.skytala.eCommerce.domain.marketing.relations.trackingCode.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.TrackingCodeFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.TrackingCodeMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.TrackingCode;


public class FindAllTrackingCodes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrackingCode> returnVal = new ArrayList<TrackingCode>();
try{
List<GenericValue> results = delegator.findAll("TrackingCode", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TrackingCodeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TrackingCodeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
