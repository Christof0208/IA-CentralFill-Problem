# Compile & Run
javac CentralFillLocator.java
java CentralFillLocator

# Assumptions
The total number of central fills is generated between 10 and 20
Medication prices are randomly generated between $10 and $100.
The Coordinates are provided in the format X,Y without spaces.

# To Support Multiple Central Fills at the Same Location

Modify the storage structure for central fills from a list (List<CentralFill>) to a Map<String, List<CentralFill>>, where the key is a string representation of the coordinates (e.g., "x,y") and the value is a list of central fills at that location.

Update the findClosestCentralFills method to handle multiple fills at the same coordinate and ensure all fills at a specific location are listed as part of the closest results.

Enhance sorting logic to include distance comparison but list all central fills at each unique coordinate before moving to the next closest coordinate.

# Scaling to a Larger World Size

Use efficient spatial data structures like quadtrees or KD-trees to quickly search for the nearest central fills.

Store central fills in a spatially indexed database (MongoDB) for optimized querying of nearby facilities.

Only load the relevant subset of facilities near the user's input instead of keeping all facilities in memory.

Use parallel streams or multi-threading to compute Manhattan distances and sort results faster for large datasets.

Cache frequently queried Manhattan distances to avoid redundant calculations.