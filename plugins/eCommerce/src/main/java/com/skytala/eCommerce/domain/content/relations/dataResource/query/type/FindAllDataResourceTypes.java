
package com.skytala.eCommerce.domain.content.relations.dataResource.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.type.DataResourceTypeFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.type.DataResourceTypeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.type.DataResourceType;


public class FindAllDataResourceTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResourceType> returnVal = new ArrayList<DataResourceType>();
try{
List<GenericValue> results = delegator.findAll("DataResourceType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataResourceTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataResourceTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
