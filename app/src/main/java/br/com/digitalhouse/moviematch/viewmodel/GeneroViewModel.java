package br.com.digitalhouse.moviematch.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.moviematch.data.database.Database;
import br.com.digitalhouse.moviematch.data.database.dao.GeneroDAO;
import br.com.digitalhouse.moviematch.model.genero.Genero;
import br.com.digitalhouse.moviematch.model.genero.GeneroResponse;
import br.com.digitalhouse.moviematch.repository.GeneroRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.moviematch.util.AppUtil.isNetworkConnected;

public class GeneroViewModel extends AndroidViewModel {

    private MutableLiveData<List<Genero>> generoLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private GeneroRepository repository = new GeneroRepository();

    public GeneroViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Genero>> getGeneroLiveData() {

        return generoLiveData;

    }

    public LiveData<Boolean> getLoadingLiveData() {

        return loadingLiveData;

    }

    public LiveData<Throwable> getErrorLiveData() {

        return errorLiveData;

    }

    public void searchGenero() {

        if (isNetworkConnected(getApplication())) {

            getApiGenero();

        } else {

            getLocalGenero();

        }
    }

    private void getLocalGenero() {

        disposable.add(
                repository.getGeneroLocal(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(false))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(generos -> generoLiveData.setValue(generos)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private void getApiGenero() {

        disposable.add(
                repository.getGeneroApi()
                        .subscribeOn(Schedulers.newThread())
                        .map(generoResponse -> {
                            return saveItems(generoResponse);
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(generoResponse -> generoLiveData.setValue(generoResponse.getGeneros())
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }


    private GeneroResponse saveItems(GeneroResponse generoResponse) {

        GeneroDAO generoDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .generoDAO();

        generoDAO.insertAll(generoResponse.getGeneros());

        return generoResponse;

    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {

        super.onCleared();
        disposable.clear();
    }
}
