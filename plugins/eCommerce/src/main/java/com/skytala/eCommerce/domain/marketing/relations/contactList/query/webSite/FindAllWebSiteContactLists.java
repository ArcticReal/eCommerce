
package com.skytala.eCommerce.domain.marketing.relations.contactList.query.webSite;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.webSite.WebSiteContactListFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.webSite.WebSiteContactListMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.webSite.WebSiteContactList;


public class FindAllWebSiteContactLists extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebSiteContactList> returnVal = new ArrayList<WebSiteContactList>();
try{
List<GenericValue> results = delegator.findAll("WebSiteContactList", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebSiteContactListMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebSiteContactListFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
