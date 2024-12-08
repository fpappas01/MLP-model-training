
public class Layer {
    private Neuron[] neurons;
    private Layer next;
    private Layer previous;
    
    public Layer(Neuron[] neurons, Layer next, Layer previous) {
        this.neurons = neurons;
        this.next = next;
        this.previous = previous;
    }
    
    public Neuron[] getNeurons() {
        return neurons;
    }

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
    }

    public Layer getNext() {
        return next;
    }

    public void setNext(Layer next) {
        this.next = next;
    }

    public Layer getPrevious() {
        return previous;
    }

    public void setPrevious(Layer previous) {
        this.previous = previous;
    }

}
