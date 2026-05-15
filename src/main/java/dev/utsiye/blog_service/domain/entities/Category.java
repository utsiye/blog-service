package dev.utsiye.blog_service.domain.entities;


public class Category {
    private final Long id;
    private final String name;

    public Category(String name){
        validate(name);
        this.id = null;
        this.name = name;
    }

    public Category(Long id, String name){
        validate(name);
        this.id = id;
        this.name = name;
    }

    public Long getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }

    private static void validate(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        if (name.length() > 100){
            throw new IllegalArgumentException("Category name cannot exceed 100 characters");
        }
    }

    @Override
    public String toString(){
        return "User{"
        + "id=" + id + ","
        + "name=" + name
        + "}";
    }
}
