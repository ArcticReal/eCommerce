
package com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.event.DataResourcePurposeFound;
import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.mapper.DataResourcePurposeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.model.DataResourcePurpose;


public class FindAllDataResourcePurposes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResourcePurpose> returnVal = new ArrayList<DataResourcePurpose>();
try{
List<GenericValue> results = delegator.findAll("DataResourcePurpose", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataResourcePurposeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataResourcePurposeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
