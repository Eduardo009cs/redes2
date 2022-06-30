package persona;

import java.io.Serializable;

public class Persona implements Serializable {

    String nombre;
    String apellido_paterno;
    transient String apellido_materno;
    transient int edad;
    double peso;
    transient float estatura;

    public Persona(String nombre, String apellido_paterno, String apellido_materno, int edad, double peso, float estatura) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" +nombre + 
                ", apellido_paterno=" + apellido_paterno + 
                ", apellido_materno=" + apellido_materno + 
                ", edad=" + edad + ", peso=" + peso + 
                ", estatura=" + estatura + '}';
    }
    
    
}
