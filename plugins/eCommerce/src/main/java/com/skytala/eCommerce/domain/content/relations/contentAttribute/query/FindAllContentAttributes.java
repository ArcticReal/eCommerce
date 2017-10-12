
package com.skytala.eCommerce.domain.content.relations.contentAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.contentAttribute.event.ContentAttributeFound;
import com.skytala.eCommerce.domain.content.relations.contentAttribute.mapper.ContentAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.contentAttribute.model.ContentAttribute;


public class FindAllContentAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentAttribute> returnVal = new ArrayList<ContentAttribute>();
try{
List<GenericValue> results = delegator.findAll("ContentAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
