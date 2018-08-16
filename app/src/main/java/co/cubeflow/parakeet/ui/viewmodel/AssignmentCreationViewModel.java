package co.cubeflow.parakeet.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.cubeflow.parakeet.model.Assignment;

/**
 * A {@link ViewModel} that contains the necessary attributes to create an {@link
 * Assignment}
 *
 * @version 1.0.0
 * @since v1.0.0 (11/21/2017)
 */
@SuppressWarnings("JavaDoc")
public class AssignmentCreationViewModel extends ViewModel {

    private MutableLiveData<String> classId;

    private MutableLiveData<String> name;

    private MutableLiveData<String> notes;

    private MutableLiveData<Long> dueDate;

    private MutableLiveData<Integer> totalPoints;

    public MutableLiveData<String> getClassId() {
        if (classId == null) {
            classId = new MutableLiveData<>();
        }
        return classId;
    }

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }

    public MutableLiveData<String> getNotes() {
        if (notes == null) {
            notes = new MutableLiveData<>();
        }
        return notes;
    }

    public MutableLiveData<Long> getDueDate() {
        if (dueDate == null) {
            dueDate = new MutableLiveData<>();
            dueDate.setValue(0L);
        }
        return dueDate;
    }

    public MutableLiveData<Integer> getTotalPoints() {
        if (totalPoints == null) {
            totalPoints = new MutableLiveData<>();
        }
        return totalPoints;
    }
}
