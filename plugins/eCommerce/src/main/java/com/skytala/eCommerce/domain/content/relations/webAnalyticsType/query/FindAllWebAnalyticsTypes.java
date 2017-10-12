
package com.skytala.eCommerce.domain.content.relations.webAnalyticsType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event.WebAnalyticsTypeFound;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.mapper.WebAnalyticsTypeMapper;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.model.WebAnalyticsType;


public class FindAllWebAnalyticsTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebAnalyticsType> returnVal = new ArrayList<WebAnalyticsType>();
try{
List<GenericValue> results = delegator.findAll("WebAnalyticsType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebAnalyticsTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebAnalyticsTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
