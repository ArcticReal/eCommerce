
package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.FixedAssetFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.FixedAssetMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.FixedAsset;


public class FindAllFixedAssets extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAsset> returnVal = new ArrayList<FixedAsset>();
try{
List<GenericValue> results = delegator.findAll("FixedAsset", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
