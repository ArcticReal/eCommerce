
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.event.FixedAssetProductFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.mapper.FixedAssetProductMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.model.FixedAssetProduct;


public class FindAllFixedAssetProducts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetProduct> returnVal = new ArrayList<FixedAssetProduct>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetProduct", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetProductMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetProductFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
