package app.shareparking.com.spapp.networking;

import java.util.List;

import app.shareparking.com.spapp.dtos.TempClass;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestService {

    @GET("/comments")
    Observable<List<TempClass>> lsitOfComments();
}
