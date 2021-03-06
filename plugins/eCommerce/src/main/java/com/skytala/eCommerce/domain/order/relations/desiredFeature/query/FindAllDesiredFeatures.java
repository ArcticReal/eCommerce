
package com.skytala.eCommerce.domain.order.relations.desiredFeature.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.event.DesiredFeatureFound;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.mapper.DesiredFeatureMapper;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.model.DesiredFeature;


public class FindAllDesiredFeatures extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DesiredFeature> returnVal = new ArrayList<DesiredFeature>();
try{
List<GenericValue> results = delegator.findAll("DesiredFeature", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DesiredFeatureMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DesiredFeatureFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
