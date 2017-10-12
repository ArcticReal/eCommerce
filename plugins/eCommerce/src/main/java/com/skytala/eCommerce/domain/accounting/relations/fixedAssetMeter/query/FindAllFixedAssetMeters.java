
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.event.FixedAssetMeterFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.mapper.FixedAssetMeterMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.model.FixedAssetMeter;


public class FindAllFixedAssetMeters extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetMeter> returnVal = new ArrayList<FixedAssetMeter>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetMeter", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetMeterMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetMeterFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
