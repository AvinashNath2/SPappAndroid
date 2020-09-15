package app.shareparking.com.spapp.networking;

import java.util.List;

import app.shareparking.com.spapp.dtos.TempClass;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestService {

    @GET("/comments")
    Observable<List<TempClass>> lsitOfComments();

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
