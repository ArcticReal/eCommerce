
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetType.event.FixedAssetTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetType.mapper.FixedAssetTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetType.model.FixedAssetType;


public class FindAllFixedAssetTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetType> returnVal = new ArrayList<FixedAssetType>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
