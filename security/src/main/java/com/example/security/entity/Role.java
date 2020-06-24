package com.example.security.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
@ToString
/**
 * 角色类
 */
public class Role {
    @Id
    private Long id;
    @Column(name = "role_name")
    private String roleName;
}
