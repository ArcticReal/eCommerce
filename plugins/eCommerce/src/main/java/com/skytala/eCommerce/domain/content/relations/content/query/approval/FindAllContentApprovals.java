
package com.skytala.eCommerce.domain.content.relations.content.query.approval;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.approval.ContentApprovalFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.approval.ContentApprovalMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.approval.ContentApproval;


public class FindAllContentApprovals extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentApproval> returnVal = new ArrayList<ContentApproval>();
try{
List<GenericValue> results = delegator.findAll("ContentApproval", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentApprovalMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentApprovalFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
