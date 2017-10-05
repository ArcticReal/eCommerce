
package com.skytala.eCommerce.domain.itemIssuance.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.itemIssuance.event.ItemIssuanceFound;
import com.skytala.eCommerce.domain.itemIssuance.mapper.ItemIssuanceMapper;
import com.skytala.eCommerce.domain.itemIssuance.model.ItemIssuance;


public class FindAllItemIssuances extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ItemIssuance> returnVal = new ArrayList<ItemIssuance>();
try{
List<GenericValue> results = delegator.findAll("ItemIssuance", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ItemIssuanceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ItemIssuanceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
