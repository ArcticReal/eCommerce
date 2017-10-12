
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.event.FixedAssetStdCostTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.mapper.FixedAssetStdCostTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.model.FixedAssetStdCostType;


public class FindAllFixedAssetStdCostTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetStdCostType> returnVal = new ArrayList<FixedAssetStdCostType>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetStdCostType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetStdCostTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetStdCostTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
