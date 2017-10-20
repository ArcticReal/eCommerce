
package com.skytala.eCommerce.domain.humanres.relations.perfReview.query.item;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemFound;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.item.PerfReviewItemMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.item.PerfReviewItem;


public class FindAllPerfReviewItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PerfReviewItem> returnVal = new ArrayList<PerfReviewItem>();
try{
List<GenericValue> results = delegator.findAll("PerfReviewItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PerfReviewItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PerfReviewItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
