package io.devcommunity.developer_community.domain.dto.request;

import io.devcommunity.developer_community.domain.entity.ProjectMember;
import io.devcommunity.developer_community.domain.entity.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectCreateReqDto {
    @NotBlank @NotNull
    private UUID userId;

    private String tile;

    private String description;

    private ProjectStatus projectStatus;

    private List<ProjectMember> members;
}
