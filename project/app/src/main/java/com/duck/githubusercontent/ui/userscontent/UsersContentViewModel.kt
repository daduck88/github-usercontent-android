package com.duck.githubusercontent.ui.userscontent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duck.githubusercontent.data.PagesRepository
import com.duck.githubusercontent.data.UsersContentRepository
import com.duck.githubusercontent.data.remote.objects.UserContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersContentViewModel @Inject constructor(
    private val pagesRepository: PagesRepository,
    private val repository: UsersContentRepository
    ) : ViewModel() {
    var uiState by mutableStateOf(UsersContentUiState(isLoading = true))
        private set

    private var fetchJob: Job? = null

    init {
        fetchUsersContent()
    }

    fun fetchUsersContent() {
        fetchJob?.cancel()
        uiState = uiState.copy(
            isLoading = true,
            errorMessage = null
        )
        fetchJob = viewModelScope.launch {
            var nextPage = ""
            try {
                val pages = pagesRepository.getPages()
                // for now just getting the first page
                pages?.let{
                    nextPage = pages[0]
                }
            } catch (e: Exception){
                // Not sure what to add here
            }
            repository.getUsersContent(nextPage).catch {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = "Something when wrong"
                )
            }.collect { items ->
                uiState = UsersContentUiState(
                    items = items,
                    isLoading = false,
                    errorMessage = null
                )
            }
        }
    }

}

data class UsersContentUiState(
    val items: List<UserContent> = listOf(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
