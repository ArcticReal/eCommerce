
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetAttribute.event.FixedAssetAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetAttribute.mapper.FixedAssetAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetAttribute.model.FixedAssetAttribute;


public class FindAllFixedAssetAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetAttribute> returnVal = new ArrayList<FixedAssetAttribute>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
