import java.util.*;

class CentralFill {
    int id;
    int x;
    int y;
    Map<String, Double> medicationPrices;

    public CentralFill(int id, int x, int y, Map<String, Double> medicationPrices) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.medicationPrices = medicationPrices;
    }

    public double getLowestPrice() {
        return Collections.min(medicationPrices.values());
    }

    public String getMedicationWithLowestPrice() {
        return medicationPrices.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    public int getManhattanDistance(int userX, int userY) {
        return Math.abs(x - userX) + Math.abs(y - userY);
    }

    @Override
    public String toString() {
        return String.format("Central Fill %03d - $%.2f, Medication %s", id, getLowestPrice(), getMedicationWithLowestPrice());
    }
}

public class CentralFillLocator {

    private static final int WORLD_MIN = -10;
    private static final int WORLD_MAX = 10;
    private static final String[] MEDICATIONS = {"A", "B", "C"};

    private int NUM_CENTRAL_FILLS = 0;
    private List<CentralFill> centralFills;

    public CentralFillLocator() {
        centralFills = generateRandomCentralFills();
    }

    private List<CentralFill> generateRandomCentralFills() {
        Random rand = new Random();
        Set<String> occupiedCoordinates = new HashSet<>();
        List<CentralFill> fills = new ArrayList<>();

        NUM_CENTRAL_FILLS = rand.nextInt(10, 20); // Total Number of central fills between 10 and 20

        for (int i = 1; i <= NUM_CENTRAL_FILLS; i++) {
            int x, y;
            do {
                x = rand.nextInt(WORLD_MAX - WORLD_MIN + 1) + WORLD_MIN;
                y = rand.nextInt(WORLD_MAX - WORLD_MIN + 1) + WORLD_MIN;
            } while (occupiedCoordinates.contains(x + "," + y));

            occupiedCoordinates.add(x + "," + y);
            Map<String, Double> medicationPrices = new HashMap<>();
            for (String medication : MEDICATIONS) {
                medicationPrices.put(medication, 10 + rand.nextDouble() * 90); // Price between 10 and 100 USD
            }
            fills.add(new CentralFill(i, x, y, medicationPrices));
        }

        return fills;
    }

    public void findClosestCentralFills(int userX, int userY) {
        centralFills.sort(Comparator.comparingInt(cf -> cf.getManhattanDistance(userX, userY)));

        System.out.println("Closest Central Fills to (" + userX + "," + userY + "):");
        for (int i = 0; i < Math.min(3, centralFills.size()); i++) {
            CentralFill fill = centralFills.get(i);
            System.out.printf("%s, Distance %d%n", fill, fill.getManhattanDistance(userX, userY));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CentralFillLocator locator = new CentralFillLocator();

        System.out.println("Please Input Coordinates (X,Y):");
        String[] input = scanner.nextLine().split(",");

        int userX = Integer.parseInt(input[0].trim());
        int userY = Integer.parseInt(input[1].trim());

        locator.findClosestCentralFills(userX, userY);

        scanner.close();
    }
}
