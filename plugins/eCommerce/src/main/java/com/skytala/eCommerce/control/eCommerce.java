package com.skytala.eCommerce.control;

import java.util.Map;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class eCommerce {
    public static final String module = eCommerce.class.getName();

    public static Map<String, Object> testFunction(DispatchContext dctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess("You have called this sample Service successfully!");
        System.out.println("Greeting Earthlings!");     
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        return result;
    }
}