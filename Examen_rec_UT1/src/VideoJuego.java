import java.io.Serializable;

public class VideoJuego implements Serializable {
    String Nombre;
    String Platform;
    Double Precio;

    public VideoJuego(String nombre, String platform, Double precio) {

        this.Nombre = nombre;
        this.Platform = platform;
        this.Precio = precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    @Override
    public String toString() {
        return "VideoJuego{" +
                "Nombre='" + Nombre + '\'' +
                ", Plataforma='" + Platform + '\'' +
                ", Precio=" + Precio +
                '}';
    }
}
