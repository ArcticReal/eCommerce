
package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.stdCost;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCost.FixedAssetStdCostFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.stdCost.FixedAssetStdCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.stdCost.FixedAssetStdCost;


public class FindAllFixedAssetStdCosts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetStdCost> returnVal = new ArrayList<FixedAssetStdCost>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetStdCost", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetStdCostMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetStdCostFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
