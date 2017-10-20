
package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.typeAttr.AcctgTransTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.typeAttr.AcctgTransTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.typeAttr.AcctgTransTypeAttr;


public class FindAllAcctgTransTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AcctgTransTypeAttr> returnVal = new ArrayList<AcctgTransTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("AcctgTransTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AcctgTransTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AcctgTransTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
