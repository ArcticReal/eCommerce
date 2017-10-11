
package com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.event.ProductCalculatedInfoFound;
import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.mapper.ProductCalculatedInfoMapper;
import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.model.ProductCalculatedInfo;


public class FindAllProductCalculatedInfos extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCalculatedInfo> returnVal = new ArrayList<ProductCalculatedInfo>();
try{
List<GenericValue> results = delegator.findAll("ProductCalculatedInfo", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCalculatedInfoMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCalculatedInfoFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
