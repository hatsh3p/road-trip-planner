package cli;

import java.util.*;

public final class CommandLine {
    private final Set<String> cities;
    private final Set<String> attractions;

    public CommandLine(Set<String> cities, Set<String> attractions) {
        this.cities = cities;
        this.attractions = attractions;
    }

    public Inputs readInputs() {
        Scanner in = new Scanner(System.in);

        String start = null;
        while (start == null) {
            start = readString(in, "Name of starting city (or EXIT to quit)");
            if (start.equals("EXIT")) {
                return null;
            }
            if (!cities.contains(start)) {
                System.out.println("City \"" + start + "\" unknown.");
                start = null;
            }
        }

        String end = null;
        while (end == null) {
            end = readString(in, "Name of ending city (or EXIT to quit)");
            if (end.equals("EXIT")) {
                return null;
            }
            if (!cities.contains(end)) {
                System.out.println("City \"" + end + "\" unknown.");
                end = null;
            }
        }

        List<String> attractions = new ArrayList<>();
        while (true) {
            String attraction = readString(in, "List an attraction along the way (or ENOUGH to stop listing)");
            if (attraction.equals("ENOUGH")) {
                break;
            }
            if (this.attractions.contains(attraction)) {
                attractions.add(attraction);
            } else {
                System.out.println("Attraction \"" + attraction + "\" unknown.");
            }
        }

        in.close();
        return new Inputs(start, end, attractions);
    }

    private String readString(Scanner scanner, String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }
}
