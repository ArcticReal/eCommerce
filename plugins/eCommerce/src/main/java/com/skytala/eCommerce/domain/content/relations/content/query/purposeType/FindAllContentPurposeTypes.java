
package com.skytala.eCommerce.domain.content.relations.content.query.purposeType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeType.ContentPurposeTypeFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.purposeType.ContentPurposeTypeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.purposeType.ContentPurposeType;


public class FindAllContentPurposeTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentPurposeType> returnVal = new ArrayList<ContentPurposeType>();
try{
List<GenericValue> results = delegator.findAll("ContentPurposeType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentPurposeTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentPurposeTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
