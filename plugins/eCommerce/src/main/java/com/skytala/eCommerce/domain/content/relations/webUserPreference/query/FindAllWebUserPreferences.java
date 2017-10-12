
package com.skytala.eCommerce.domain.content.relations.webUserPreference.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.event.WebUserPreferenceFound;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.mapper.WebUserPreferenceMapper;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.model.WebUserPreference;


public class FindAllWebUserPreferences extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebUserPreference> returnVal = new ArrayList<WebUserPreference>();
try{
List<GenericValue> results = delegator.findAll("WebUserPreference", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebUserPreferenceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebUserPreferenceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
