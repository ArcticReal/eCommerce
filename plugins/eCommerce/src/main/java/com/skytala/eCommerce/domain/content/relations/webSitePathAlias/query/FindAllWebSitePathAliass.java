
package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event.WebSitePathAliasFound;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.mapper.WebSitePathAliasMapper;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;


public class FindAllWebSitePathAliass extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebSitePathAlias> returnVal = new ArrayList<WebSitePathAlias>();
try{
List<GenericValue> results = delegator.findAll("WebSitePathAlias", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebSitePathAliasMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebSitePathAliasFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
