
package com.skytala.eCommerce.domain.content.relations.dataResource.query.other;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.other.OtherDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.other.OtherDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.other.OtherDataResource;


public class FindAllOtherDataResources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OtherDataResource> returnVal = new ArrayList<OtherDataResource>();
try{
List<GenericValue> results = delegator.findAll("OtherDataResource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OtherDataResourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OtherDataResourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
