
package com.skytala.eCommerce.domain.content.relations.content.query.purposeOperation;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeOperation.ContentPurposeOperationFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.purposeOperation.ContentPurposeOperationMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.purposeOperation.ContentPurposeOperation;


public class FindAllContentPurposeOperations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentPurposeOperation> returnVal = new ArrayList<ContentPurposeOperation>();
try{
List<GenericValue> results = delegator.findAll("ContentPurposeOperation", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentPurposeOperationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentPurposeOperationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
