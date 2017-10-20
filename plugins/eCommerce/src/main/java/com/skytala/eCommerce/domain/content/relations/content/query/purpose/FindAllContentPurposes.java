
package com.skytala.eCommerce.domain.content.relations.content.query.purpose;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.purpose.ContentPurposeFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.purpose.ContentPurposeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.purpose.ContentPurpose;


public class FindAllContentPurposes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentPurpose> returnVal = new ArrayList<ContentPurpose>();
try{
List<GenericValue> results = delegator.findAll("ContentPurpose", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentPurposeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentPurposeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
