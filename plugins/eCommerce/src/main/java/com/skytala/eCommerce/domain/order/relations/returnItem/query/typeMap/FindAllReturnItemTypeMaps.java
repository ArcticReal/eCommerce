
package com.skytala.eCommerce.domain.order.relations.returnItem.query.typeMap;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap.ReturnItemTypeMapFound;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.typeMap.ReturnItemTypeMapMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.typeMap.ReturnItemTypeMap;


public class FindAllReturnItemTypeMaps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnItemTypeMap> returnVal = new ArrayList<ReturnItemTypeMap>();
try{
List<GenericValue> results = delegator.findAll("ReturnItemTypeMap", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnItemTypeMapMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnItemTypeMapFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
