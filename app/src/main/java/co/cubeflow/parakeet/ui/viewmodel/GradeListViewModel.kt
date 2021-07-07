package co.cubeflow.parakeet.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.cubeflow.parakeet.model.Assignment

/**
 * @version 1.0.0
 * @since v1.0.0 (11/21/2017)
 */
class GradeListViewModel : ViewModel() {

    private val _assignments = MutableLiveData<List<Assignment>>()

    val assignments: LiveData<List<Assignment>> = _assignments

    fun init() {
    }
}
