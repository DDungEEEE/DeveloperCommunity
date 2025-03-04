package io.devcommunity.developer_community.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "projects")
public class Projects extends BaseEntity {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status", nullable = false)
    private ProjectStatus projectStatus; // RECRUITING, IN_PROGRESS, COMPLETED

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectMember> members;

    @Builder
    public Projects(String title, String description, ProjectStatus projectStatus){
        this.title = title;
        this.description = description;
        this.projectStatus = projectStatus;
    }

    public void updateOwner(Users users){
        this.owner = users;
    }
}
