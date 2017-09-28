package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPromo;
import com.skytala.eCommerce.event.ProductPromoAdded;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class AddProductPromo implements Command {

private ProductPromo elementToBeAdded;
public AddProductPromo(ProductPromo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public void execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try {
elementToBeAdded.setProductPromoId(delegator.getNextSeqId("ProductPromo"));
GenericValue newValue = delegator.makeValue("ProductPromo", elementToBeAdded.mapAttributeField());
delegator.create(newValue);
success = true;
} catch(GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}

Broker.instance().publish(new ProductPromoAdded(success));
}
}
