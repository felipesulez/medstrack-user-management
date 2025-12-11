package com.medstrack.Medstrack.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterUserDTO {

    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "El correo no es valido")
    private String correo;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El password no puede estar vacio")
    @Size(min=8, max = 12, message = "El password debe tener entre 8 y 12 caracteres")
    private String password;

    //Getters and Setters

    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;

    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
