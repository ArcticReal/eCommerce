
package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event.WebAnalyticsConfigFound;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.mapper.WebAnalyticsConfigMapper;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model.WebAnalyticsConfig;


public class FindAllWebAnalyticsConfigs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebAnalyticsConfig> returnVal = new ArrayList<WebAnalyticsConfig>();
try{
List<GenericValue> results = delegator.findAll("WebAnalyticsConfig", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebAnalyticsConfigMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebAnalyticsConfigFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
