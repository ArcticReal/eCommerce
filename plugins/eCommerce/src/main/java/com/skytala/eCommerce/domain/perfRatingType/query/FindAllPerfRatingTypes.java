
package com.skytala.eCommerce.domain.perfRatingType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.perfRatingType.event.PerfRatingTypeFound;
import com.skytala.eCommerce.domain.perfRatingType.mapper.PerfRatingTypeMapper;
import com.skytala.eCommerce.domain.perfRatingType.model.PerfRatingType;


public class FindAllPerfRatingTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PerfRatingType> returnVal = new ArrayList<PerfRatingType>();
try{
List<GenericValue> results = delegator.findAll("PerfRatingType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PerfRatingTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PerfRatingTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
