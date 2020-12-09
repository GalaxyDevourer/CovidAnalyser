package models.datamining.algorithms;

import com.opencsv.bean.CsvToBeanBuilder;
import models.csv.entities.CentroidItem;
import models.datamining.distances.Distance;
import models.datamining.entities.Centroid;
import models.datamining.entities.Country;
import models.datamining.entities.mappers.CentroidMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class KMeans {
    private Random random = new Random();
    private String filePath = "";

    public Map<Centroid, List<Country>> startAnalysis (List<Country> countries,
        int k, Distance distance, int maxIterations, Boolean isRandom) throws FileNotFoundException {

        List<Centroid> centroids = initCentroids(countries, k, isRandom);
        Map<Centroid, List<Country>> clusters = new HashMap<>();
        Map<Centroid, List<Country>> lastState = new HashMap<>();

        for (int i = 0; i < maxIterations; i++) {
            for (Country country : countries) {
                Centroid centroid = nearestCentroid(country, centroids, distance);
                assignToCluster(clusters, country, centroid);
            }

            boolean shouldTerminate = clusters.equals(lastState);
            lastState = clusters;
            if (shouldTerminate) {
                break;
            }

            centroids = relocateCentroids(clusters);
            clusters = new HashMap<>();

        }

        clusters.forEach( (x,y) -> {
            System.out.println("Centroid: " + x);
            y.forEach(System.out::println);
            System.out.println("----------------------------------------------");
        });

        return clusters;
    }

    private List<Centroid> initCentroids(List<Country> countries, int k, Boolean isRandom) throws FileNotFoundException {
        List<Centroid> centroids;

        if (isRandom) {
            centroids = randomCentroids(countries, k);
        }
        else centroids = customCentroids();

        System.out.println("---------------Centroids----------------");
        centroids.forEach(System.out::println);
        System.out.println("---------------Centroids----------------");

        return centroids;
    }

    private List<Centroid> randomCentroids (List<Country> countries, int k) {
        List<Centroid> centroids = new ArrayList<>();
        Map<String, Double> maxs = new HashMap<>();
        Map<String, Double> mins = new HashMap<>();

        for (Country country : countries) {
            country.getFeatures().forEach((key, value) -> {
                maxs.compute(key, (k1, max) -> max == null || value > max ? value : max);

                mins.compute(key, (k1, min) -> min == null || value < min ? value : min);
            });
        }

        Set<String> attributes = countries.stream()
                .flatMap(e -> e.getFeatures().keySet().stream())
                .collect(Collectors.toSet());
        for (int i = 0; i < k; i++) {
            Map<String, Double> coordinates = new HashMap<>();
            for (String attribute : attributes) {
                double max = maxs.get(attribute);
                double min = mins.get(attribute);
                coordinates.put(attribute, random.nextDouble() * (max - min) + min);
            }

            centroids.add(new Centroid(coordinates));
        }

        return centroids;
    }

    private List<Centroid> customCentroids () throws FileNotFoundException {
        List<CentroidItem> centroidItemList = new CsvToBeanBuilder<CentroidItem>(new FileReader(filePath))
                .withType(CentroidItem.class).build().parse();

        return new CentroidMapper().buildFinalCentroidList(centroidItemList);
    }

    private Centroid nearestCentroid (Country record, List<Centroid> centroids, Distance distance) {
        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            double currentDistance = distance.calculate(record.getFeatures(), centroid.getCoordinates());

            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = centroid;
            }
        }

        return nearest;
    }

    private void assignToCluster (Map<Centroid, List<Country>> clusters, Country record, Centroid centroid) {
        clusters.compute(centroid, (key, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(record);
            return list;
        });
    }

    private Centroid average (Centroid centroid, List<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            return centroid;
        }

        Map<String, Double> average = centroid.getCoordinates();
        countries.stream().flatMap(e -> e.getFeatures().keySet().stream())
                .forEach(k -> average.put(k, 0.0));

        for (Country country : countries) {
            country.getFeatures().forEach(
                    (k, v) -> average.compute(k, (k1, currentValue) -> v + currentValue)
            );
        }

        average.forEach((k, v) -> average.put(k, v / countries.size()));

        return new Centroid(average);
    }

    private List<Centroid> relocateCentroids (Map<Centroid, List<Country>> clusters) {
        return clusters.entrySet().stream().map(e -> average(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
