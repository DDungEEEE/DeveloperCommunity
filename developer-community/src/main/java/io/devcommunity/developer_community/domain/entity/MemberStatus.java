package io.devcommunity.developer_community.domain.entity;

import lombok.Getter;

@Getter
public enum MemberStatus {
    PENDING("대기"), ACCEPTED("승인"), REJECTED("거절");

    private final String description;

    MemberStatus(String description) {
        this.description = description;
    }
}
