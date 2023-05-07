package ui.algorithm;

import ui.model.DataInput;

public interface Algorithm {

    void fit(DataInput training);

    void predict(DataInput testing);

}
