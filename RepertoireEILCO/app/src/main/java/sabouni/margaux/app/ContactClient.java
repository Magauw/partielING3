package sabouni.margaux.app;

import java.util.List;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ContactClient {

    @GET("eilco.json")
    Call<List<Contact> > ListContact();

}

