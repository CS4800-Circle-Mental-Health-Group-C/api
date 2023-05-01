package com.circle.api.repository;

import com.circle.api.model.Circle;

public interface CircleRepository {
    
    public Circle findMemberInfoByIds(String userId, String circleMemberId);
}
