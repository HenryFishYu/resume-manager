package com.resume.manager.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "resumes")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "name" }) })
public class ResumeEntity extends BaseEntity{
    private Long userId;
    private String name;
}
