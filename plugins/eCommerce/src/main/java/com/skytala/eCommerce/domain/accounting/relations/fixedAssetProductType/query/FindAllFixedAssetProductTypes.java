
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.event.FixedAssetProductTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.mapper.FixedAssetProductTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.model.FixedAssetProductType;


public class FindAllFixedAssetProductTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetProductType> returnVal = new ArrayList<FixedAssetProductType>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetProductType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetProductTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetProductTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}