
package com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event.ProductCategoryTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.mapper.ProductCategoryTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.model.ProductCategoryTypeAttr;


public class FindAllProductCategoryTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryTypeAttr> returnVal = new ArrayList<ProductCategoryTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
