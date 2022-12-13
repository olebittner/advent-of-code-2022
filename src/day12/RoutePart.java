package day12;

public class RoutePart implements Comparable<RoutePart> {

    private final int currentNode;
    private Integer previousNode;
    private double routeScore;
    private double estimatedScore;

    public RoutePart(int currentNode) {
        this(currentNode, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public RoutePart(int currentNode, Integer previousNode, double routeScore, double estimatedScore) {
        this.currentNode = currentNode;
        this.previousNode = previousNode;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    public int getCurrentNode() {
        return currentNode;
    }

    public Integer getPreviousNode() {
        return previousNode;
    }

    public double getRouteScore() {
        return routeScore;
    }

    public double getEstimatedScore() {
        return estimatedScore;
    }

    public void setPreviousNode(Integer previousNode) {
        this.previousNode = previousNode;
    }

    public void setRouteScore(double routeScore) {
        this.routeScore = routeScore;
    }

    public void setEstimatedScore(double estimatedScore) {
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(RoutePart o) {
        if (this.estimatedScore > o.estimatedScore)
            return 1;
        if (this.estimatedScore < o.estimatedScore)
            return -1;
        return 0;
    }
}
