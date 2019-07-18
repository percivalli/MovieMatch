package br.com.digitalhouse.moviematch.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.moviematch.data.database.Database;
import br.com.digitalhouse.moviematch.data.database.dao.FilmeDAO;
import br.com.digitalhouse.moviematch.data.database.dao.UsuarioDAO;
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

    public void searchFilme(long generoId) {

        if (isNetworkConnected(getApplication())) {

            getApiFilme(generoId);

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

    private void getApiFilme(long generoId) {

        disposable.add(
                repository.getFilmeApi(generoId)
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

        UsuarioDAO usuarioDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .usuarioDAO();

        //Atualiza os filmes já selecionados anteriormente pelo Usuario
        for (Filme linhaFilme : filmeResponse.getFilmes()) {
            if (usuarioDAO.getByIdFilme(linhaFilme.getId()) != 0) { //Filme selecionado
                linhaFilme.setFilmeSelecionado(true);
            }
        }

        filmeDAO.deleteAll();
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
