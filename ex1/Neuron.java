
public class Neuron {
    private double value;
    private double error;
    private double bias;
    private double biasDerivative;
    private Connection[] connectionsNext;
    private Connection[] connectionsPrevious;

    public Neuron(Connection[] connectionsNext, Connection[] connectionsPrevious) {
        this.value = 0;
        this.error = 0;
        this.connectionsNext = connectionsNext;
        this.connectionsPrevious = connectionsPrevious;
        this.bias = 1;
        this.biasDerivative = 0;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public Connection[] getConnectionsNext() {
        return connectionsNext;
    }

    public void setConnectionsNext(Connection[] connectionsNext) {
        this.connectionsNext = connectionsNext;
    }

    public Connection[] getConnectionsPrevious() {
        return connectionsPrevious;
    }

    public void setConnectionsPrevious(Connection[] connectionsPrevious) {
        this.connectionsPrevious = connectionsPrevious;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getBiasDerivative() {
        return biasDerivative;
    }

    public void setBiasDerivative(double biasDerivative) {
        this.biasDerivative = biasDerivative;
    }

    
}
