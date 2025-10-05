package com.soict.code.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;
    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;
    private String language;
    private String submissionCode;
    private String status;
    private String result;
}