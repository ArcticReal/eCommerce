
package com.skytala.eCommerce.domain.content.relations.dataResource.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute.DataResourceAttributeFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.attribute.DataResourceAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.attribute.DataResourceAttribute;


public class FindAllDataResourceAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResourceAttribute> returnVal = new ArrayList<DataResourceAttribute>();
try{
List<GenericValue> results = delegator.findAll("DataResourceAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataResourceAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataResourceAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
