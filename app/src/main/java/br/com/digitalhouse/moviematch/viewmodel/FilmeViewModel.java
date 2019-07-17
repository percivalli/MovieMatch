package br.com.digitalhouse.moviematch.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.moviematch.data.database.Database;
import br.com.digitalhouse.moviematch.data.database.dao.FilmeDAO;
import br.com.digitalhouse.moviematch.model.filme.Filme;
import br.com.digitalhouse.moviematch.model.filme.FilmeResponse;
import br.com.digitalhouse.moviematch.repository.FilmeRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.moviematch.util.AppUtil.isNetworkConnected;

public class FilmeViewModel extends AndroidViewModel {

    private MutableLiveData<List<Filme>> filmeLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private FilmeRepository repository = new FilmeRepository();

    public FilmeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Filme>> getFilmeLiveData() {

        return filmeLiveData;

    }

    public LiveData<Boolean> getLoadingLiveData() {

        return loadingLiveData;

    }

    public LiveData<Throwable> getErrorLiveData() {

        return errorLiveData;

    }

    public void searchFilme() {

        if (isNetworkConnected(getApplication())) {

            getApiFilme();

        } else {

            getLocalFilme();

        }
    }

    private void getLocalFilme() {

        disposable.add(
                repository.getFilmeLocal(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(false))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(filmes -> filmeLiveData.setValue(filmes)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private void getApiFilme() {

        disposable.add(
                repository.getFilmeApi()
                        .subscribeOn(Schedulers.newThread())
                        .map(filmeResponse -> saveItems(filmeResponse))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(filmeResponse -> filmeLiveData.setValue(filmeResponse.getFilmes())
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }


    private FilmeResponse saveItems(FilmeResponse filmeResponse) {

        FilmeDAO filmeDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .filmeDAO();

        filmeDAO.insertAll(filmeResponse.getFilmes());

        return filmeResponse;

    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {

        super.onCleared();
        disposable.clear();
    }
}
