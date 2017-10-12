
package com.skytala.eCommerce.domain.content.relations.contentRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.contentRole.event.ContentRoleFound;
import com.skytala.eCommerce.domain.content.relations.contentRole.mapper.ContentRoleMapper;
import com.skytala.eCommerce.domain.content.relations.contentRole.model.ContentRole;


public class FindAllContentRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentRole> returnVal = new ArrayList<ContentRole>();
try{
List<GenericValue> results = delegator.findAll("ContentRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
