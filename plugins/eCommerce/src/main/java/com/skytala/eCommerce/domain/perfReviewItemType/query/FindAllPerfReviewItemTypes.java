
package com.skytala.eCommerce.domain.perfReviewItemType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.perfReviewItemType.event.PerfReviewItemTypeFound;
import com.skytala.eCommerce.domain.perfReviewItemType.mapper.PerfReviewItemTypeMapper;
import com.skytala.eCommerce.domain.perfReviewItemType.model.PerfReviewItemType;


public class FindAllPerfReviewItemTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PerfReviewItemType> returnVal = new ArrayList<PerfReviewItemType>();
try{
List<GenericValue> results = delegator.findAll("PerfReviewItemType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PerfReviewItemTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PerfReviewItemTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}