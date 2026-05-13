package dev.utsiye.blog_service.domain.entities;

import dev.utsiye.blog_service.domain.enums.UserRole;


public class User {
    private final Long id;
    private final String name;
    private final UserRole role;
    private final String passwordHash;

    public User(String name, UserRole role, String passwordHash){
        validate(name, passwordHash);

        this.id = null;
        this.name = name;
        this.role = role;
        this.passwordHash = passwordHash;
    }

    public User(Long id, String name, UserRole role, String passwordHash){
        validate(name, passwordHash);
        this.id = id;
        this.name = name;
        this.role = role;
        this.passwordHash = passwordHash;
    }

    public Long getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }

    public UserRole getRole(){
        return role;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    private static void validate(String name, String passwordHash){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (name.length() > 100){
            throw new IllegalArgumentException("Username cannot exceed 100 characters");
        }

        if (passwordHash == null || passwordHash.trim().isEmpty()){
            throw new IllegalArgumentException("Password hash cannot be empty");
        }
    }

    @Override
    public String toString(){
        return "User{"
        + "id=" + id + ","
        + "name=" + name + ","
        + "role=" + role.name()
        + "}";
    }
}
