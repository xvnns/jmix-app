package com.company.jmixtest1.security;

import com.company.jmixtest1.entity.Request;
import io.jmix.security.model.SecurityScope;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;

import static io.jmix.security.model.EntityAttributePolicyAction.MODIFY;
import static io.jmix.security.model.EntityPolicyAction.*;

@ResourceRole(name = RequestRole.NAME, code = RequestRole.NAME, scope = SecurityScope.API)
public interface RequestRole {

    String NAME = "request-role";

    @EntityPolicy(entityClass = Request.class, actions = ALL)
    @EntityAttributePolicy(entityClass = Request.class, attributes = "*", action = MODIFY)
    @SpecificPolicy(resources = "*")
    void access();
}