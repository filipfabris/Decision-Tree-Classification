package ui.algorithm;

import ui.model.DataSet;

public interface Algorithm {

    void fit(DataSet training);

    void predict(DataSet testing);
}
