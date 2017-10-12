
package com.skytala.eCommerce.domain.content.relations.dataResource.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.DataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.DataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.DataResource;


public class FindAllDataResources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResource> returnVal = new ArrayList<DataResource>();
try{
List<GenericValue> results = delegator.findAll("DataResource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataResourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataResourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
