
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.event.FixedAssetGeoPointFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.mapper.FixedAssetGeoPointMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.model.FixedAssetGeoPoint;


public class FindAllFixedAssetGeoPoints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetGeoPoint> returnVal = new ArrayList<FixedAssetGeoPoint>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetGeoPoint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetGeoPointMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetGeoPointFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
