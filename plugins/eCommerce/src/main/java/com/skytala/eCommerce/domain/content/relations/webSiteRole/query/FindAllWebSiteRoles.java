
package com.skytala.eCommerce.domain.content.relations.webSiteRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.event.WebSiteRoleFound;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.mapper.WebSiteRoleMapper;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.model.WebSiteRole;


public class FindAllWebSiteRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebSiteRole> returnVal = new ArrayList<WebSiteRole>();
try{
List<GenericValue> results = delegator.findAll("WebSiteRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebSiteRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebSiteRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
