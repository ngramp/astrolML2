import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
import org.deeplearning4j.nn.conf.LearningRatePolicy;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.graph.MergeVertex;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.dataset.api.iterator.MultiDataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.prefs.Preferences;

/**
 * A reimplementation of Dieleman et al. (2015) neural network
 * @see "http://deeplearning4j.org/compgraph"
 * @see "https://github.com/deeplearning4j/dl4j-examples/blob/8f91f87cd8ef03da71edd7b670f7c5979daa007f/dl4j-examples/src/main/java/org/deeplearning4j/examples/recurrent/seq2seq/CustomSequenceIterator.java"
 * TODO: implement early stopping and model saving
 */
public class DielemanNet {
    private static final Logger log = LoggerFactory.getLogger(DielemanNet.class);
    private static final Preferences prefs = Preferences.userRoot().node("Dieleman");
    private MultiDataSetIterator training;
    private MultiDataSetIterator testing;
    private int outputNum = 3;
    //with a batch size of 16
    private int batchSize;
    private int nEpochs;
    private int channels;
    private int width, height;
    private int iterations;
    private double learningRate;
    private double biasLearningRate;
    private double momentum;
    private final int seed = 123;
    ComputationGraph model;

    DielemanNet(MultiDataSetIterator training, MultiDataSetIterator testing){
        log.info("Load data....");
        this.training = training;
        this.testing = testing;
        this.nEpochs = prefs.getInt("nEpochs",10);
        this.batchSize = prefs.getInt("batchSize",16);
        this.outputNum = prefs.getInt("outputNum",37);
        this.iterations = prefs.getInt("iterations",1);
        this.learningRate = prefs.getDouble("learningRate",0.04);
        this.biasLearningRate = prefs.getDouble("biasLearningRate",2 * 1e-2);
        this.momentum = prefs.getDouble("momentum",0.9);
        buildModel();
    }


    void train(){
        log.info("Train model....");

        model.setListeners(new ScoreIterationListener(1));
        for (int i = 0; i < nEpochs; i++) {
            model.fit(training);
            log.info("*** Completed epoch {} ***", i);
        }
        log.info("****************Example finished********************");
    }

    private void buildModel() {
        log.info("Build model....");
        NeuralNetConfiguration.Builder conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .iterations(iterations)
                .activation("relu")
                //To train the models we used minibatch gradient descent
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                //We began with a constant learning rate η = 0.04
                .learningRate(learningRate) // TODO reduce by 4% every 8 epochs - paper is 1e-4
                .biasLearningRate(biasLearningRate)
                .learningRateDecayPolicy(LearningRatePolicy.Step)
                .lrPolicyDecayRate(0.96)
                .lrPolicySteps(320000)
                // and  Nesterov momentum (Bengio, Boulanger-Lewandowski & Pascanu 2013) with coefficient μ = 0.9.
                .updater(Updater.NESTEROVS)
                .momentum(momentum)
                .weightInit(WeightInit.XAVIER)
                .regularization(true)
                .l2(2e-4);

        ComputationGraphConfiguration.GraphBuilder graphBuilder = conf.graphBuilder();
        //Here we build 16 groups of convolutional layers, each with its own input
        for (int i = 0; i < 16; i++) {
            graphBuilder.addInputs("input-" + i)
                    .setInputTypes(InputType.convolutional(height, width, channels))
                    //There are four convolutional layers, all with square filters, with filter sizes
                    // 6, 5, 3 and 3, respectively, and with untied biases
                    .addLayer("cnn1-" + i, new ConvolutionLayer.Builder()
                            .kernelSize(40, 40)
                            .nIn(channels)
                            .nOut(32)
                            .stride(6, 6)
                            .biasInit(0.1)
                            .build(), "input-" + i)
                    //2 by 2 max-pooling follows the first,second and fourth convolutional layers.
                    .addLayer("max1-" + i, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                            .stride(2, 2)
                            .build(), "cnn1-" + i)
                    .addLayer("cnn2-" + i, new ConvolutionLayer.Builder()
                            .kernelSize(16, 16)
                            .stride(5, 5)
                            .nOut(64)
                            .biasInit(0.1)
                            .build(), "max1-" + i)
                    .addLayer("max2-" + i, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                            .stride(2, 2)
                            .build(), "cnn2-" + i)
                    .addLayer("cnn3-" + i, new ConvolutionLayer.Builder()
                            .kernelSize(6, 6)
                            .stride(3, 3)
                            .nOut(128)
                            .biasInit(0.1)
                            .build(), "max2-" + i)
                    .addLayer("cnn4-" + i, new ConvolutionLayer.Builder()
                            .kernelSize(4, 4)
                            .stride(3, 3)
                            .nOut(128)
                            .biasInit(0.1)
                            .build(), "cnn3-" + i)
                    .addLayer("max3-" + i, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                            .stride(2, 2)
                            .build(), "cnn4-" + i);
        }
        // "Maxout layers were used instead of ReLU layers to reduce the number of connections to the next layer
        // (and thus the number of parameters). We did not use maxout
        // in the convolutional layers because it proved too computationally intensive."

        //here we merge the outputs of all 16 groups before training a single fully connected network
        graphBuilder.addVertex("merge", new MergeVertex(), "max3-0", "max3-1", "max3-2", "max3-3", "max3-4", "max3-5", "max3-6", "max3-7", "max3-8", "max3-9", "max3-10", "max3-11", "max3-12", "max3-13", "max3-14", "max3-15")
                .addLayer("ffn1", new DenseLayer.Builder()
                        .nOut(2048)
                        .dropOut(0.4)
                        .activation("maxout")
                        .l2(1)
                        .biasInit(0.01)
                        .build())
                .addLayer("ffn2", new DenseLayer.Builder()
                        .nOut(2048)
                        .dropOut(0.4)
                        .activation("maxout")
                        .l2(1)
                        .biasInit(0.01)
                        .build())
                .addLayer("output", new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(outputNum)
                        .activation("softmax")
                        .build())
                .setOutputs("output")
                .backprop(true).pretrain(false);
        ComputationGraphConfiguration done = graphBuilder.build();

        ComputationGraph model = new ComputationGraph(done);
        model.init();
        this.model = model;
    }

}