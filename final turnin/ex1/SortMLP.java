import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SortMLP {
    private int d; //inputs
    private int k; //outputs
    private int h1;
    private int h2;
    private int h3;
    private double learningRate;
    private double terminationThreshold;
    private Layer[] layers;
    private int B;
    private Example[] trainData;
    private Example[] testData;
    private double totalError;
    private boolean isRelu = false;
    private final DataCreatorSDT dataCreatorSDT = new DataCreatorSDT();

    public SortMLP(int d, int k, int h1, int h2, int h3, double learningRate, double terminationThreshold, int B, boolean isRelu) {
        this.d = d;
        this.k = k;
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
        this.learningRate = learningRate;
        this.terminationThreshold = terminationThreshold;
        this.B = B;
        this.totalError = 0;
	this.isRelu = isRelu;
        initializer();
    }

    public void execute() throws IOException {
        double tempError = 0;
        int epochCounter = 0;
        do {
            tempError = totalError;
            totalError = 0;
            trainModel();
            System.out.println("total error: " + totalError);
            System.out.println("Epoch: " + epochCounter +", Generalization ability: " + getGeneralizationAbility() * 100 + "%");
            epochCounter++;
            initializeDerivatives();
        } while (epochCounter < 800 || !endCondition(tempError, totalError) && epochCounter < 2000);
    }

    private void initializeDerivatives() {
        for (Layer layer : layers) {
            for (Neuron neuron : layer.getNeurons()) {
                neuron.setBiasDerivative(0);
                if (neuron.getConnectionsNext() != null) {
                    for (Connection connection : neuron.getConnectionsNext()) {
                        connection.setDerivative(0);
                    }
                }
                if (neuron.getConnectionsPrevious() != null) {
                    for (Connection connection : neuron.getConnectionsPrevious()) {
                        connection.setDerivative(0);
                    }
                }
            }
        }    
    }

    public double[] forwardPass(Example x) {
        // input layer
        double[] input = exampleToDouble(x);
        double[] output = new double[k];
        layers[0].getNeurons()[0].setValue(input[0]);
        layers[0].getNeurons()[1].setValue(input[1]);
        updateConnectionsValues();
        // hidden layers
        Neuron[] neurons = new Neuron[0]; 
        double preActivationVal = 0;
        for (int i = 1; i < layers.length - 1; i++) {
            neurons = layers[i].getNeurons();  
            for (int j = 0; j < neurons.length; j++) {
                preActivationVal = sumOfPreviouslayer(neurons[j]);
                if (i == layers.length - 2 && isRelu) neurons[j].setValue(reluAF(preActivationVal + neurons[j].getBias()));
                else neurons[j].setValue(tahnAF(preActivationVal + neurons[j].getBias()));
                updateConnectionsValues();
            }
        }
        calculateOutputLayerValues(layers[layers.length - 1]);
        updateConnectionsValues();
        neurons = layers[layers.length - 1].getNeurons(); 
        for (int i = 0; i < output.length; i++) {
            output[i] = neurons[i].getValue();
        }
        return output;
    }

    public void backprop(Example x, double[] t) {
        double[] trueOutput = forwardPass(x);
        this.totalError += getOutputError(trueOutput, t);
        // errors for output layer
        double error = 0;
        for (int i = 0; i < k; i++) {
            error = trueOutput[i] * (1 - trueOutput[i]) * (trueOutput[i] - t[i]);
            layers[layers.length - 1].getNeurons()[i].setError(error);
        }
        // errors for hidden layers
        for (int i = layers.length - 2; i >= 1; i--) {
            calculateHiddenLayerErrors(layers[i]);
        }
        // calculate derivatives
        for (int i = 1; i < layers.length; i++) {
            calculateNewDerivatives(layers[i]);
        }
    }


    public void trainModel() {
        double[][] wantedOutputs = getEncodedOutputs(trainData);
        int batchCounter = 0;
        for (int i = 0; i < trainData.length; i++) {
            if (batchCounter == B) {
                for (Layer layer : layers) {
                    if (layer.getPrevious() != null) calculateNewWeights(layer);
                }
                batchCounter = 0;
            }
            backprop(trainData[i], wantedOutputs[i]);
            batchCounter++;
        }
    }

    public double getGeneralizationAbility() {
        double correctResultsSum = 0;
        for (Example example : testData) {
            if (outputIsCorrect(example, forwardPass(example))) correctResultsSum++;
        }
        return correctResultsSum/testData.length;
    }

    private double getOutputError(double[] trueOutput, double[] t) {
        double sum = 0;
        for (int i = 0; i < t.length; i++) {
            sum += Math.pow(t[i] - trueOutput[i], 2);
        }
        return sum * 0.5;
    } 

    private boolean outputIsCorrect(Example example, double[] passOutput) {
        String categoryOutput = "";
        double maxProb = Math.max(Math.max(passOutput[0], passOutput[1]), Math.max(passOutput[2], passOutput[3]));
        if (maxProb == passOutput[0]) categoryOutput = "C1";
        else if (maxProb == passOutput[1]) categoryOutput = "C2";
        else if (maxProb == passOutput[2]) categoryOutput = "C3";
        else if (maxProb == passOutput[3]) categoryOutput = "C4";
        return categoryOutput.equals(example.getCategory());
    }

    private void updateConnectionsValues() {
        Neuron[] tmpNeurons;
        Connection[] tmpConnections;
        for (Layer layer : layers) {
            if (layer.getPrevious() != null) {
                tmpNeurons = layer.getPrevious().getNeurons();
                for (Neuron neuron : layer.getNeurons()) {
                    tmpConnections = neuron.getConnectionsPrevious();
                    for (int i = 0; i < tmpConnections.length; i++) {
                        tmpConnections[i].setFromValue(tmpNeurons[i].getValue());
                    }
                }
            }
            if (layer.getNext() != null) {
                tmpNeurons = layer.getNext().getNeurons();
                for (Neuron neuron : layer.getNeurons()) {
                    tmpConnections = neuron.getConnectionsNext();
                    for (int i = 0; i < tmpConnections.length; i++) {
                        tmpConnections[i].setToValue(tmpNeurons[i].getValue());
                    }
                } 
            }
        }
    }

    private double[][] getEncodedOutputs(Example[] trainData) {
        double[][] wantedOutputs = new double[trainData.length][k];
        for (int i = 0; i < trainData.length; i++) {
            if (trainData[i].getCategory().equals("C1")) wantedOutputs[i] = new double[]{1, 0, 0, 0};
            else if (trainData[i].getCategory().equals("C2")) wantedOutputs[i] = new double[]{0, 1, 0, 0};
            else if (trainData[i].getCategory().equals("C3")) wantedOutputs[i] = new double[]{0, 0, 1, 0};
            else wantedOutputs[i] = new double[]{0, 0, 0, 1};
        }
        return wantedOutputs;
    }

    private void calculateNewWeights(Layer layer) {
        Neuron[] neurons = layer.getNeurons();
        Connection[] connections;
        double newWeight = 0;
        for (Neuron neuron : neurons) {
            neuron.setBias(neuron.getBias() - learningRate * neuron.getBiasDerivative());
            connections = neuron.getConnectionsPrevious();
            for (int i = 0; i < connections.length; i++) {
                newWeight = connections[i].getWeight() - (learningRate * connections[i].getDerivative());
                connections[i].setWeight(newWeight);
            }
        }
    }

    private void calculateNewDerivatives(Layer layer) {
        Neuron[] neurons = layer.getNeurons();
        Connection[] connections;
        double oldDerivative = 0, newDerivative = 0; 
        for (Neuron neuron : neurons) {
            neuron.setBiasDerivative(neuron.getBiasDerivative() + neuron.getError());
            connections = neuron.getConnectionsPrevious();
            for (int i = 0; i < connections.length; i++) {
                oldDerivative = connections[i].getDerivative();
                newDerivative = oldDerivative + neuron.getError() * connections[i].getFromValue();
                connections[i].setDerivative(newDerivative);
            }
        }
    }

    private void calculateHiddenLayerErrors(Layer layer) {
        Neuron[] neurons = layer.getNeurons();
        double error = 0;
        for (int i = 0; i < neurons.length; i++) {
            if (layer == layers[layers.length - 2] && isRelu) {
                if (neurons[i].getValue() == 0) error = 0;
                else error = sumProductsOfNextLayer(neurons[i], layer.getNext());
            }
            else error = (1 - Math.pow(neurons[i].getValue(), 2))  * sumProductsOfNextLayer(neurons[i], layer.getNext());
            neurons[i].setError(error);
        }
    }

    private double sumProductsOfNextLayer(Neuron neuron, Layer nextLayer) {
        Connection[] connections = neuron.getConnectionsNext();
        Neuron[] nextNeurons = nextLayer.getNeurons();
        double sum = 0;
        for (int i = 0; i < nextNeurons.length; i++) {
            sum += connections[i].getWeight() * nextNeurons[i].getError();
        }
        return sum;
    }

    private void calculateOutputLayerValues(Layer outputLayer) { 
        Neuron[] neurons;
        double preActivationVal = 0;
        neurons = outputLayer.getNeurons();
        for (int j = 0; j < neurons.length; j++) {
            preActivationVal = sumOfPreviouslayer(neurons[j]) + neurons[j].getBias();
            neurons[j].setValue(sigmoid(preActivationVal));
        }
    }

    private double sigmoid(double input) {
        return 1/(1 + Math.exp(-input));
    }

    private double sumOfPreviouslayer(Neuron neuron) {
        double sum = 0;
        for (Connection connection : neuron.getConnectionsPrevious()) {
            sum += connection.getWeight() * connection.getFromValue();
        }
        return sum;
    }

    private double[] exampleToDouble(Example example) {
        PointSDT point = example.getPoint();
        double[] tmp = new double[2];
        tmp[0] = (double) point.getX();
        tmp[1] = (double) point.getY();
        return tmp;
    } 

    public double tahnAF(double x) {
        double a = 0.25;
        return (Math.exp(a * x) - Math.exp(-a * x))/(Math.exp(a * x) + Math.exp(-a * x)) ;
    }

    public double reluAF(double x) {
        return Math.max(x, 0);
    }


    public boolean endCondition(double totalError1, double totalError2) {
        // System.out.println("Error: " + Math.abs(totalError1 - totalError2));
        return Math.abs(totalError1 - totalError2) <= terminationThreshold;
    }

    private void initializer() {
        int[] tmpAmounts = {0, h1, h2, h3};
        int layersAmount = (h3 == 0) ? 4 : 5; 
        this.layers = new Layer[layersAmount];
        for (int i = 0; i < layersAmount; i++) {
            layers[i] = new Layer(null, null, null);
        }
        for (int i = 0; i < layersAmount - 1; i++) {
            layers[i].setNext(layers[i + 1]);
            if (i == 0) {
                layers[i].setNeurons(new Neuron[d]);
            }
            else if (i == layersAmount - 2) {
                layers[i].setPrevious(layers[i - 1]);
                layers[i].setNeurons(new Neuron[tmpAmounts[i]]);
                layers[i + 1].setNeurons(new Neuron[k]);
                layers[i + 1].setPrevious(layers[i]);
            }
            else {
                layers[i].setPrevious(layers[i - 1]);
                layers[i].setNeurons(new Neuron[tmpAmounts[i]]);
            }
        }
        for (int i = 0; i < layersAmount; i++) {
            layers[i].setNeurons(getInitializedNeurons(layers[i].getNeurons().length, layers[i].getPrevious(), layers[i].getNext()));
        }
        initializeData();
    }

    private void initializeData() {
        int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
        dataCreatorSDT.createDataSDT();
        Map<String, ArrayList<PointSDT>> sdt = dataCreatorSDT.getDataSDT();
        Example[][] randoms = getRandomExamples(sdt);
        this.trainData = randoms[0];
        for (Example example : trainData) {
            if (example.getCategory() == "C1") c1++;
            else if (example.getCategory() == "C2") c2++;
            else if (example.getCategory() == "C3") c3++;
            else c4++;
        }
        System.out.println("C1: " + c1);
        System.out.println("C2: " + c2);
        System.out.println("C3: " + c3);
        System.out.println("C4: " + c4);
        this.testData = randoms[1];
    }

    private Example[][] getRandomExamples(Map<String, ArrayList<PointSDT>> sdt) {
        Example[][] examples = new Example[2][4000];
        List<Example> values = new ArrayList<>();
        Iterator iterator = sdt.keySet().iterator();
        String key;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            for (PointSDT point : sdt.get(key)) {
                values.add(new Example(point, key));
            }
        }
        Collections.shuffle(values);
        for (int i = 0; i < examples.length; i++) {
            for (int j = 0; j < examples[i].length; j++) {
                examples[i][j] = values.get(j);
            }
            values = values.subList(examples[i].length, values.size());
        }
        return examples;
    }


    private Neuron[] getInitializedNeurons(int len, Layer prevLayer, Layer nextLayer) {
        Neuron[] neurons = new Neuron[len];
        for (int i = 0; i < len; i++) {
            neurons[i] = new Neuron(null, null);
            if (prevLayer != null) neurons[i].setConnectionsPrevious(getInitializedPreviousConnections(prevLayer, i));
            if (nextLayer != null) neurons[i].setConnectionsNext(getInitializedNextConnections(nextLayer));
        }
        return neurons;
    }

    private Connection[] getInitializedNextConnections(Layer nextLayer) {
        Connection[] connections = new Connection[nextLayer.getNeurons().length];
        for (int i = 0; i < connections.length; i++) {
            connections[i] = new Connection(0, 0, 0);
            connections[i].setWeight(getRandomWeights(-1, 1));
        }
        return connections;
    }

    private Connection[] getInitializedPreviousConnections(Layer prevLayer, int neuronIndex) {
        Connection[] connections = new Connection[prevLayer.getNeurons().length];
        for (int i = 0; i < connections.length; i++) {
            connections[i] = prevLayer.getNeurons()[i].getConnectionsNext()[neuronIndex];
        }
        return connections;
    }

    private double getRandomWeights(double min, double max) {
        Random random = new Random();
        double weight = 0;
        do {
            weight = min + random.nextDouble() * (max - min);
        } while (weight == 0);
        return weight;
    }

}

