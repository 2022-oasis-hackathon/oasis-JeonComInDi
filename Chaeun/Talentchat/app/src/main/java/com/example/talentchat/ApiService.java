package com.example.talentchat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //   기반 주소                     |  GET  |    Query     |
    //   http://192.168.0.29:3001/api/auth/me?x-access-token=[String data]
    @GET("auth/me")
    Call<ResponseBody> getFunc(@Header("x-access-token") String data);


    //   기반 주소                     |  GET  |    Field     |
    //   http://192.168.0.29:3001/api/auth/me?data=[String data]
    @FormUrlEncoded
    @POST("auth/me")
    Call<ResponseBody> postFunc(@Field("data") String data);

    //   기반 주소                     |  PUT               |    Field     |
    //   http://192.168.0.29:3001/api/auth/put/{String id}?data=[String data]
    @FormUrlEncoded
    @PUT("auth/put/{id}")
    Call<ResponseBody> putFunc(@Path("id") String id, @Field("data") String data);

    //   기반 주소                     |  DELETE               |
    //   http://192.168.0.29:3001/api/auth/delete/{String id}
    @DELETE("auth/delete/{id}")
    Call<ResponseBody> deleteFunc(@Path("id") String id);
}