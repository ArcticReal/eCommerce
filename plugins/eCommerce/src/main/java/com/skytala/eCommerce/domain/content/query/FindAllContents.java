
package com.skytala.eCommerce.domain.content.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.event.ContentFound;
import com.skytala.eCommerce.domain.content.mapper.ContentMapper;
import com.skytala.eCommerce.domain.content.model.Content;


public class FindAllContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Content> returnVal = new ArrayList<Content>();
try{
List<GenericValue> results = delegator.findAll("Content", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
