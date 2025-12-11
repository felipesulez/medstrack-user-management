package com.medstrack.Medstrack.model;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;
    @Column(unique = true)
    private String correo;
    private String password;

    public User() {}

    public User(long id, String nombre, String correo, String password){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public long getId(){ return id;}
    public void setId(long id){this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCorreo(){return correo;}
    public void setCorreo(String correo){this.correo = correo;}

    public String getPassword(){return password;}
    public void setPassword(String password){ this.password = password;}


}
