
package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event.WebSitePublishPointFound;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.mapper.WebSitePublishPointMapper;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;


public class FindAllWebSitePublishPoints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebSitePublishPoint> returnVal = new ArrayList<WebSitePublishPoint>();
try{
List<GenericValue> results = delegator.findAll("WebSitePublishPoint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebSitePublishPointMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebSitePublishPointFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
