package com.example.architecturebase.mvvm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturebase.domain.UsesCasesGetPosts
import com.example.architecturebase.mvvm.notifier.INotifier
import com.example.architecturebase.mvvm.notifier.NotifierUpdater
import com.example.architecturebase.network.IPostApi
import com.example.architecturebase.network.model.Post

class MvvmViewModel : ViewModel(), MvvmContract.IViewModel {

    private val getUsesCasesGetPosts = UsesCasesGetPosts()
    override val posts: MutableLiveData<List<Post>> = MutableLiveData()

    override fun loadPosts() {
        getUsesCasesGetPosts.getPosts{
            // в mvp у нас была view и мы могли бы установить значение полуяенное
            // из usesCase напрямую, я не знаю как мне это сделать здесь,
            // точнее я понимаю, что надо как-то обновить переменную posts и она автоматически
            // будет обновлена и в view, но что-то не понимаю как мне это сделать
        }
    }
}