
package com.skytala.eCommerce.domain.product.relations.product.query.storeGroupMember;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember.ProductStoreGroupMemberFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupMember.ProductStoreGroupMemberMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupMember.ProductStoreGroupMember;


public class FindAllProductStoreGroupMembers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreGroupMember> returnVal = new ArrayList<ProductStoreGroupMember>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreGroupMember", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreGroupMemberMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreGroupMemberFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
