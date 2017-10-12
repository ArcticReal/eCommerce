
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.event.FixedAssetTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.mapper.FixedAssetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.model.FixedAssetTypeAttr;


public class FindAllFixedAssetTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetTypeAttr> returnVal = new ArrayList<FixedAssetTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
