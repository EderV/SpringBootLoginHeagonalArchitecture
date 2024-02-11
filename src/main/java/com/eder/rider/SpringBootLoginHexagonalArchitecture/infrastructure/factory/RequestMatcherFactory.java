package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.factory;

import org.springframework.security.web.util.matcher.RequestMatcher;

public interface RequestMatcherFactory {

    RequestMatcher getRequestMatcher(String matcher);

}
