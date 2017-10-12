
package com.skytala.eCommerce.domain.content.relations.dataTemplateType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.event.DataTemplateTypeFound;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.mapper.DataTemplateTypeMapper;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.model.DataTemplateType;


public class FindAllDataTemplateTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataTemplateType> returnVal = new ArrayList<DataTemplateType>();
try{
List<GenericValue> results = delegator.findAll("DataTemplateType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataTemplateTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataTemplateTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
