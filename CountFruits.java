import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class CountFruits {

    public static void main(String[] args) {
        // Crear un arreglo de objetos de tipo Fruta
        Fruit [] fruits = {
                new Fruit("platano", true),
                new Fruit("melon", true),
                new Fruit("chirimoya", true),
                new Fruit("pera", true),
                new Fruit("piña", false),
                new Fruit("mandarina", false),
                new Fruit("naranja", false),
                new Fruit("lima", false),
                new Fruit("limon", false),
              
        };

        // contar frutas dulces y amargos
        long startTime = System.currentTimeMillis();
        int countSweet = parallelCount(fruits, true);
        int countBitter = parallelCount(fruits, false);
        long endTime = System.currentTimeMillis();

        // Imprimir resultados
        System.out.println("Número de frutas dulces: " + countSweet);
        System.out.println("Número de frutas amargas: " + countBitter);
        // System.out.println("Número de frutas dulces: " + countBitter);
        System.out.println("Tiempo de ejecución con programación paralela: " + (endTime - startTime) + " ms");

        // Contar frutas dulces y amargas  programación paralela
        startTime = System.currentTimeMillis();
        int countSweetSequential = sequentialCount(fruits, true);
        int countBitterSequential = sequentialCount(fruits, false);
        endTime = System.currentTimeMillis();

        // Imprimir resultados
        System.out.println("Número de frutas dulces y amargas programación paralela: " + countSweetSequential);
        System.out.println("Número de frutas dulces y amargas programación paralela: " + countBitterSequential);
        System.out.println("Tiempo de ejecución sin programación paralela: " + (endTime - startTime) + " ms");
    }

    public static int sequentialCount(Fruit[] fruits, boolean sweet) {
        int count = 0;
        for (Fruit fruit: fruits) {
            if (fruit.sweet() == sweet) {
                count++;
            }
        }
        return count;
    }

    public static int parallelCount(Fruit[] fruits, boolean sweet) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        int chunkSize = (int) Math.ceil((double) fruits.length / numThreads);

        int[] results = new int[numThreads];

        IntStream.range(0, numThreads)
                .mapToObj(i -> new CountTask(fruits, i * chunkSize, Math.min((i + 1) * chunkSize, fruits.length), results, i, sweet))
                .forEach(executor::execute);

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Esperar a que todas las tareas terminen
        }

        return IntStream.of(results).sum();
    }

    public static class CountTask implements Runnable {
        private final Fruit[] fruits;
        private final int start;
        private final int end;
        private final int[] results;
        private final int index;
        private final boolean sweet;
    
        /**
         * @param animals
         * @param start
         * @param end
         * @param results
         * @param index
         * @param sweet
         */
        public CountTask(Fruit[] fruits, int start, int end, int[] results, int index, boolean sweet) {
            this.fruits = fruits;
            this.start = start;
            this.end = end;
            this.results = results;
            this.index = index;
            this.sweet = sweet;
        }

        

        @Override
        public void run() {
            int count = 0;
            for (int i = start; i < end; i++) {
                if (fruits[i].sweet() == sweet) {
                    count++;
                }
            }
            results[index] = count;
        }   

    }  
}