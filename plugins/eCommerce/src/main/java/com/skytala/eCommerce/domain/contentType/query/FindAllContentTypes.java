
package com.skytala.eCommerce.domain.contentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.contentType.event.ContentTypeFound;
import com.skytala.eCommerce.domain.contentType.mapper.ContentTypeMapper;
import com.skytala.eCommerce.domain.contentType.model.ContentType;


public class FindAllContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentType> returnVal = new ArrayList<ContentType>();
try{
List<GenericValue> results = delegator.findAll("ContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
