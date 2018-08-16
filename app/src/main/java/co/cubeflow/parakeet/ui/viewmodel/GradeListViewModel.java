package co.cubeflow.parakeet.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.cubeflow.parakeet.model.Assignment;

import java.util.List;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/21/2017)
 */
public class GradeListViewModel extends ViewModel {

    private MutableLiveData<List<Assignment>> assignment;

    public MutableLiveData<List<Assignment>> getAssignments() {
        if (assignment == null) {
            assignment = new MutableLiveData<>();
        }
        return assignment;
    }



}
