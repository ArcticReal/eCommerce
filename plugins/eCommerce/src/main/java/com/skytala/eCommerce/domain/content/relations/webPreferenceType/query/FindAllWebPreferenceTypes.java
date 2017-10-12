
package com.skytala.eCommerce.domain.content.relations.webPreferenceType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeFound;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.mapper.WebPreferenceTypeMapper;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.model.WebPreferenceType;


public class FindAllWebPreferenceTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WebPreferenceType> returnVal = new ArrayList<WebPreferenceType>();
try{
List<GenericValue> results = delegator.findAll("WebPreferenceType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WebPreferenceTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WebPreferenceTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
