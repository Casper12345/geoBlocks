import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class GeoClusters {

  private int width;
  private HashSet<Integer> setOfGeos = new LinkedHashSet<>();

  /**
   * Constructor Method.
   *
   * @param width width of cluster
   */
  public GeoClusters(int width) throws CustomException {
    this.width = width;
  }

  /**
   * Method for finding geoClusters.
   *
   * @param inputGeos occupied geos.
   * @return number of connected geos.
   */
  public HashMap<Integer, HashSet<Integer>> clusterCounter(List<Integer> inputGeos) {

    setOfGeos.addAll(inputGeos);

    HashMap<Integer, HashSet<Integer>> neighborMap = new LinkedHashMap<>();

    while (setOfGeos.iterator().hasNext()) {
      int i = setOfGeos.iterator().next();
      setOfGeos.remove(i);
      neighborMap.put(i, findNeighbors(i));
    }

    neighborMap.forEach((key, value) -> value.add(key));

    return neighborMap;


  }

  /**
   * Breadth first search of occupied neighbors.
   *
   * @param geo geo to locate neighbors.
   * @return HashMap of neighbor geos.
   */
  private HashSet<Integer> findNeighbors(int geo) {

    HashSet<Integer> openSet = new LinkedHashSet<>();
    HashSet<Integer> toReturn = new HashSet<>();

    openSet.add(geo);

    while (openSet.iterator().hasNext()) {

      int n = openSet.iterator().next();

      int[] arr = {n - 1, n + 1, n - width, n + width};

      for (int index : arr) {

        if (setOfGeos.contains(index)) {
          if (index != n + 1 || index % width != 0 || width == 1) {
            setOfGeos.remove(index);
            toReturn.add(index);
            openSet.add(index);
          }
        }

      }
      openSet.remove(n);

    }
    return toReturn;
  }


}
