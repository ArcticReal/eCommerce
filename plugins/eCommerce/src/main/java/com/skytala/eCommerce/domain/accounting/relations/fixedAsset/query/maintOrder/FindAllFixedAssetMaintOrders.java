
package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.maintOrder;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder.FixedAssetMaintOrderFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.maintOrder.FixedAssetMaintOrderMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintOrder.FixedAssetMaintOrder;


public class FindAllFixedAssetMaintOrders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetMaintOrder> returnVal = new ArrayList<FixedAssetMaintOrder>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetMaintOrder", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetMaintOrderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetMaintOrderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
