
package com.skytala.eCommerce.domain.content.relations.webSiteContentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.event.WebSiteContentTypeFound;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.mapper.WebSiteContentTypeMapper;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.model.WebSiteContentType;


public class FindAllWebSiteContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebSiteContentType> returnVal = new ArrayList<WebSiteContentType>();
try{
List<GenericValue> results = delegator.findAll("WebSiteContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebSiteContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebSiteContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
