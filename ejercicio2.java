import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> ciudades = new ArrayList<>();
        ciudades.add("Puno");
        ciudades.add("Juliaca");
        ciudades.add("Lima");
        ciudades.add("Loreto");
        ciudades.add("Puerto Madonado");
        ciudades.add("Jauja");
        ciudades.add("Ilave");
        for (int i = 0; i < ciudades.size(); i++) {
            System.out.println(ciudades.get(i));

        }
        System.out.println();
        for (String ciudad : ciudades) {
            System.out.println(ciudad);
        }

        System.out.println();
        ciudades.stream().forEach(s -> System.out.println(s));

        System.out.println();

        ciudades.stream().parallel().forEach(s -> System.out.println(s));
        System.out.println("Hello world!");
    }
}