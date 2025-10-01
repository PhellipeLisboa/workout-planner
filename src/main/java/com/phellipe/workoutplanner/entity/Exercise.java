package com.phellipe.workoutplanner.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercicios",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"name", "equipment"})
        })
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descricao")
    private String description;

    @ElementCollection(targetClass = MuscleGroup.class)
    @CollectionTable(
            name = "grupo_muscular_exercicio",
            joinColumns = @JoinColumn(name = "exercicio_id")
    )

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_muscular")
    private List<MuscleGroup> muscleGroups;

    @Enumerated(EnumType.STRING)
    @Column(name = "equipamento")
    private Equipment equipment;
}
