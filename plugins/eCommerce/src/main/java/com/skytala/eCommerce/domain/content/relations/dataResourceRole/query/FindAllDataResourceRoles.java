
package com.skytala.eCommerce.domain.content.relations.dataResourceRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResourceRole.event.DataResourceRoleFound;
import com.skytala.eCommerce.domain.content.relations.dataResourceRole.mapper.DataResourceRoleMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourceRole.model.DataResourceRole;


public class FindAllDataResourceRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResourceRole> returnVal = new ArrayList<DataResourceRole>();
try{
List<GenericValue> results = delegator.findAll("DataResourceRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataResourceRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataResourceRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
