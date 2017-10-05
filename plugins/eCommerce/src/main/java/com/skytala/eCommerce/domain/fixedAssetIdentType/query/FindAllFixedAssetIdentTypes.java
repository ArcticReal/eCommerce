
package com.skytala.eCommerce.domain.fixedAssetIdentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.fixedAssetIdentType.event.FixedAssetIdentTypeFound;
import com.skytala.eCommerce.domain.fixedAssetIdentType.mapper.FixedAssetIdentTypeMapper;
import com.skytala.eCommerce.domain.fixedAssetIdentType.model.FixedAssetIdentType;


public class FindAllFixedAssetIdentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetIdentType> returnVal = new ArrayList<FixedAssetIdentType>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetIdentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetIdentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetIdentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
