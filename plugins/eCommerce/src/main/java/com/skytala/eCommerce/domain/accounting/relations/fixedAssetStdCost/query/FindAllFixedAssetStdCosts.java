
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event.FixedAssetStdCostFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.mapper.FixedAssetStdCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model.FixedAssetStdCost;


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
