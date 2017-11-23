package com.skytala.eCommerce.framework.util;

import static com.skytala.eCommerce.service.login.util.SecurityGroups.*;

public class AuthorizeMethods {

    public static final String HAS_USER_AUTHORITY = "hasAuthority('" + USER+"')";
    public static final String HAS_ADMIN_AUTHORITY = "hasAuthority('" + ADMIN+"')";
    public static final String AUTHENTICATED = "isAuthenticated()";
    public static final String PERMIT_ALL = "permitAll()";
    public static final String DENY_ALL = "denyAll()";

}
