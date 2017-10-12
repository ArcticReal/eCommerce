
package com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.event.DataResourceTypeAttrFound;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.mapper.DataResourceTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.model.DataResourceTypeAttr;


public class FindAllDataResourceTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResourceTypeAttr> returnVal = new ArrayList<DataResourceTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("DataResourceTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataResourceTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataResourceTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
