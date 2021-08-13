package cli;

import java.util.*;

public final class CommandLine {
    private final Set<String> cities;
    private final Set<String> attractions;
    private Scanner scanner;

    public CommandLine(Set<String> attractions, Set<String> cities) {
        this.attractions = attractions;
        this.cities = cities;
    }


    public Inputs readInputs() {
        printHeader();
        scanner = new Scanner(System.in);

        String start = null;
        String EXIT_MESSAGE = "EXIT";
        while (start == null) {
            start = readString(scanner, "Name of starting city (or EXIT to quit)");
            if (start.equalsIgnoreCase(EXIT_MESSAGE)) {
                return null;
            }
            if (!cities.contains(start)) {
                System.out.println("City \"" + start + "\" unknown.");
                start = null;
            }
        }

        String end = null;
        while (end == null) {
            end = readString(scanner, "Name of ending city (or EXIT to quit)");
            if (end.equalsIgnoreCase(EXIT_MESSAGE)) {
                return null;
            }
            if (!cities.contains(end)) {
                System.out.println("City \"" + end + "\" unknown.");
                end = null;
            }
        }

        List<String> attractions = new ArrayList<>();
        while (true) {
            String attraction = readString(scanner, "List an attraction along the way (or ENOUGH to stop listing)");
            String ENOUGH_MESSAGE = "ENOUGH";
            if (attraction.equalsIgnoreCase(ENOUGH_MESSAGE)) {
                break;
            }
            if (this.attractions.contains(attraction)) {
                attractions.add(attraction);
            } else {
                System.out.println("Attraction \"" + attraction + "\" unknown.");
            }
        }

        return new Inputs(start, end, attractions);
    }

    private String readString(Scanner scanner, String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    private void printHeader() {
        System.out.println("=================================");
        System.out.println("Plan a new trip");
    }
    public void close() {
        scanner.close();
    }
}
