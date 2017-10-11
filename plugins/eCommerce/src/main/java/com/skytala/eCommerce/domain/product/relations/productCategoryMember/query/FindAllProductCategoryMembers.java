
package com.skytala.eCommerce.domain.product.relations.productCategoryMember.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.event.ProductCategoryMemberFound;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.mapper.ProductCategoryMemberMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.model.ProductCategoryMember;


public class FindAllProductCategoryMembers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryMember> returnVal = new ArrayList<ProductCategoryMember>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryMember", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryMemberMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryMemberFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
