package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProdCatalog;
import com.skytala.eCommerce.event.ProdCatalogAdded;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class AddProdCatalog implements Command {

private ProdCatalog elementToBeAdded;
public AddProdCatalog(ProdCatalog elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public void execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try {
elementToBeAdded.setProdCatalogId(delegator.getNextSeqId("ProdCatalog"));
GenericValue newValue = delegator.makeValue("ProdCatalog", elementToBeAdded.mapAttributeField());
delegator.create(newValue);
success = true;
} catch(GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}

Broker.instance().publish(new ProdCatalogAdded(success));
}
}
