package com.netdeal.Fullstack.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "individual")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Individual implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than or equal to 100 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Score is mandatory")
    @Column(name = "score")
    private Integer score;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Individual individualParent;

    @JsonManagedReference
    @OneToMany(mappedBy = "individualParent", cascade = CascadeType.ALL)
    private List<Individual> subIndividuals;

    public void addSubPerson(Individual subIndividual) {
        this.subIndividuals.add(subIndividual);
    }
}