package co.cubeflow.parakeet.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.cubeflow.parakeet.model.Classroom;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/25/2017)
 */
public class ClassCreationViewModel extends ViewModel {

    private MutableLiveData<Classroom> classroom = new MutableLiveData<>();

    public LiveData<Classroom> getClassroom() {
        if (classroom == null) {
            classroom = new MutableLiveData<>();
        }
        return classroom;
    }

    public void setClassName(String name) {
        classroom.getValue().setName(name);
    }
}
