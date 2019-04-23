package com.frostinteractive.loginservice.domain;

import java.util.List;

public final class URIComparator {

    public static boolean compareURIs(List<String> allPolicies, String requestURI){
        return allPolicies.contains(requestURI);
    }
}
