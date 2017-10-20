
package com.skytala.eCommerce.domain.content.relations.content.query.operation;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.operation.ContentOperationFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.operation.ContentOperationMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.operation.ContentOperation;


public class FindAllContentOperations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentOperation> returnVal = new ArrayList<ContentOperation>();
try{
List<GenericValue> results = delegator.findAll("ContentOperation", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentOperationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentOperationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
