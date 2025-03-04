package io.devcommunity.developer_community.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "outsourcing_project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutsourcingProject {

    @Id
    @Column(name = "outsourcing_project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outsourcingProjectId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Users client;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal budget;

    @Column(nullable = false)
    private LocalDate deadline;

}