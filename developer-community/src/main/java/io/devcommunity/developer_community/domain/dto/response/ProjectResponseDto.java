package io.devcommunity.developer_community.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.devcommunity.developer_community.domain.entity.ProjectStatus;
import io.devcommunity.developer_community.domain.entity.Projects;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectResponseDto {

    private UUID ownerId;

    private String title;

    private String description;

    private ProjectStatus projectStatus;

    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public static ProjectResponseDto of(Projects projects){
        return ProjectResponseDto.builder()
                .ownerId(projects.getOwner().getUserId())
                .title(projects.getTitle())
                .description(projects.getDescription())
                .projectStatus(projects.getProjectStatus())
                .createdAt(projects.getCreatedAt())
                .updatedAt(projects.getUpdatedAt())
                .build();
    }

    public static List<ProjectResponseDto> of(List<Projects> projects){
        return projects.stream().map(ProjectResponseDto::of).collect(Collectors.toList());
    }
}
