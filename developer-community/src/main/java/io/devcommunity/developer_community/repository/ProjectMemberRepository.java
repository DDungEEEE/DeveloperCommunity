package io.devcommunity.developer_community.repository;

import io.devcommunity.developer_community.domain.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
}
