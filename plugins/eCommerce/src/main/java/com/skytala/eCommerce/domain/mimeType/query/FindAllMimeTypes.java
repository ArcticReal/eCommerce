
package com.skytala.eCommerce.domain.mimeType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.mimeType.event.MimeTypeFound;
import com.skytala.eCommerce.domain.mimeType.mapper.MimeTypeMapper;
import com.skytala.eCommerce.domain.mimeType.model.MimeType;


public class FindAllMimeTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MimeType> returnVal = new ArrayList<MimeType>();
try{
List<GenericValue> results = delegator.findAll("MimeType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MimeTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MimeTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
