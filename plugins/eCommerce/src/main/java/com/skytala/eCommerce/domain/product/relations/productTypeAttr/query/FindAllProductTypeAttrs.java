
package com.skytala.eCommerce.domain.product.relations.productTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productTypeAttr.event.ProductTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.productTypeAttr.mapper.ProductTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.productTypeAttr.model.ProductTypeAttr;


public class FindAllProductTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductTypeAttr> returnVal = new ArrayList<ProductTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("ProductTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
