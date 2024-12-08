
public class Connection {
    private double weight;
    private double fromValue;
    private double toValue;
    private double derivative;
    
    public Connection(double weight, double fromValue, double toValue) {
        this.weight = weight;
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.derivative = 0;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDerivative() {
        return derivative;
    }

    public void setDerivative(double derivative) {
        this.derivative = derivative;
    }

    public double getFromValue() {
        return fromValue;
    }

    public void setFromValue(double fromValue) {
        this.fromValue = fromValue;
    }

    public double getToValue() {
        return toValue;
    }

    public void setToValue(double toValue) {
        this.toValue = toValue;
    }

    

    
}
