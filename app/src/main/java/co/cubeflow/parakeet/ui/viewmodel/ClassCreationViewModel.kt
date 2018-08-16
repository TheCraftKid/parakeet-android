package co.cubeflow.parakeet.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import co.cubeflow.parakeet.model.Classroom

/**
 * @version 1.0.0
 * @since v1.0.0 (11/25/2017)
 */
class ClassCreationViewModel : ViewModel() {

    private val _classroom: MutableLiveData<Classroom> = MutableLiveData()

    val classrooms: LiveData<Classroom> = _classroom

    fun setClassName(name: String) {
        _classroom.value?.name = name
    }
}
