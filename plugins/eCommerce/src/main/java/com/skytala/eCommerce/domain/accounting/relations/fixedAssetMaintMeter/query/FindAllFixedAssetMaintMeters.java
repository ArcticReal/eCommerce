
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.event.FixedAssetMaintMeterFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.mapper.FixedAssetMaintMeterMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.model.FixedAssetMaintMeter;


public class FindAllFixedAssetMaintMeters extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetMaintMeter> returnVal = new ArrayList<FixedAssetMaintMeter>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetMaintMeter", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetMaintMeterMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetMaintMeterFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
