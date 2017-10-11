
package com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.event.ProductPriceAutoNoticeFound;
import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.mapper.ProductPriceAutoNoticeMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.model.ProductPriceAutoNotice;


public class FindAllProductPriceAutoNotices extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPriceAutoNotice> returnVal = new ArrayList<ProductPriceAutoNotice>();
try{
List<GenericValue> results = delegator.findAll("ProductPriceAutoNotice", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPriceAutoNoticeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPriceAutoNoticeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
