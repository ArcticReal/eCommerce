
package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.depMethod;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod.FixedAssetDepMethodFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.depMethod.FixedAssetDepMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.depMethod.FixedAssetDepMethod;


public class FindAllFixedAssetDepMethods extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetDepMethod> returnVal = new ArrayList<FixedAssetDepMethod>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetDepMethod", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetDepMethodMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetDepMethodFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
