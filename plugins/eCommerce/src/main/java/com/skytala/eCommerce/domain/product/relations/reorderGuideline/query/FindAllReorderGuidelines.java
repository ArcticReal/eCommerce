
package com.skytala.eCommerce.domain.product.relations.reorderGuideline.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.event.ReorderGuidelineFound;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.mapper.ReorderGuidelineMapper;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.model.ReorderGuideline;


public class FindAllReorderGuidelines extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReorderGuideline> returnVal = new ArrayList<ReorderGuideline>();
try{
List<GenericValue> results = delegator.findAll("ReorderGuideline", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReorderGuidelineMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReorderGuidelineFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
