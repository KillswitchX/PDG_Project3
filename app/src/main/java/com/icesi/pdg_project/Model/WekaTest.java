package com.icesi.pdg_project.Model;

import android.content.Context;
import android.os.Environment;
import android.provider.CalendarContract;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;

public class WekaTest {


    private BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"Download/"+filename);

        try {
            inputReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Log.e("ERRORXD:", ex.getMessage());
        }

        return inputReader;
    }

    private Evaluation classify(Classifier model,
                                      Instances trainingSet, Instances testingSet) throws Exception {
        Evaluation evaluation = new Evaluation(trainingSet);

        model.buildClassifier(trainingSet);
        evaluation.evaluateModel(model, testingSet);

        return evaluation;
    }

    private double calculateAccuracy(FastVector predictions) {
        double correct = 0;

        for (int i = 0; i < predictions.size(); i++) {
            NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
            if (np.predicted() == np.actual()) {
                correct++;
            }
        }

        return 100 * correct / predictions.size();
    }

    private Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
        Instances[][] split = new Instances[2][numberOfFolds];

        for (int i = 0; i < numberOfFolds; i++) {
            split[0][i] = data.trainCV(numberOfFolds, i);
            split[1][i] = data.testCV(numberOfFolds, i);
        }

        return split;
    }

    public void ejecution() throws Exception {

        BufferedReader datafile = readDataFile("churn2.txt");
        Instances data = new Instances(datafile);
        data.setClassIndex(data.numAttributes() - 1);

        // Do 10-split cross validation
        Instances[][] split = crossValidationSplit(data, 10);

        // Separate split into training and testing arrays
        Instances[] trainingSplits = split[0];
        Instances[] testingSplits = split[1];


        J48 tree = new J48();
        tree.setBinarySplits(true);
        tree.setCollapseTree(true);
        tree.setUnpruned(false);
        tree.setConfidenceFactor(0.000001f);



        // Run for each model
        for (int j = 0; j < 1; j++) {

            // Collect every group of predictions for current model in a FastVector
            FastVector predictions = new FastVector();

            // For each training-testing split pair, train and test the classifier
            for (int i = 0; i < trainingSplits.length; i++) {
                Evaluation validation = classify(tree, trainingSplits[i], testingSplits[i]);

                predictions.appendElements(validation.predictions());

                // Uncomment to see the summary for each training-testing pair.
                //System.out.println(models[j].toString());
            }

            // Calculate overall accuracy of current classifier on all splits
            double accuracy = calculateAccuracy(predictions);

            // Print current classifier's name and accuracy in a complicated,
            // but nice-looking way.
            Log.i("INFOXD: ","Accuracy of " + "J48 tree" + ": "
                    + String.format("%.2f%%", accuracy)
                    + "\n---------------------------------");
        }

        Log.i("INFOXD: " , tree.graph());



    }
}
