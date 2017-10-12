
package com.skytala.eCommerce.domain.content.relations.webSiteContent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webSiteContent.event.WebSiteContentFound;
import com.skytala.eCommerce.domain.content.relations.webSiteContent.mapper.WebSiteContentMapper;
import com.skytala.eCommerce.domain.content.relations.webSiteContent.model.WebSiteContent;


public class FindAllWebSiteContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebSiteContent> returnVal = new ArrayList<WebSiteContent>();
try{
List<GenericValue> results = delegator.findAll("WebSiteContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebSiteContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebSiteContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
