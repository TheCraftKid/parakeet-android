package co.cubeflow.parakeet.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * A [ViewModel] that contains the necessary attributes to create an [ ]
 *
 * @version 1.0.0
 * @since v1.0.0 (11/21/2017)
 */
class AssignmentCreationViewModel : ViewModel() {

    private val _classId = MutableLiveData<String>()

    private val _name = MutableLiveData<String>()

    private val _notes = MutableLiveData<String>()

    private val _dueDate = MutableLiveData<Long>()

    private var totalPoints = MutableLiveData<Int>()
}
