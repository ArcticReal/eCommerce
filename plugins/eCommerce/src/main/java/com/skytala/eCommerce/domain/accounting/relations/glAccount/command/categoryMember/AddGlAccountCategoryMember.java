package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.categoryMember;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember.GlAccountCategoryMemberAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.categoryMember.GlAccountCategoryMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryMember.GlAccountCategoryMember;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountCategoryMember extends Command {

private GlAccountCategoryMember elementToBeAdded;
public AddGlAccountCategoryMember(GlAccountCategoryMember elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountCategoryMember addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("GlAccountCategoryMember", elementToBeAdded.mapAttributeField());
addedElement = GlAccountCategoryMemberMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountCategoryMemberAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
