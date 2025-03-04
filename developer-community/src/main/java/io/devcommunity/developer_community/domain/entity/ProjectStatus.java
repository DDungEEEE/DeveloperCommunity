package io.devcommunity.developer_community.domain.entity;

import lombok.Getter;

@Getter
public enum ProjectStatus{
    RECRUITING("모집 중"),
    IN_PROGRESS("진행 중"),
    COMPLETED("완료 됨");

    private final String description;

    ProjectStatus(String description) {
        this.description = description;
    }
}
