package com.example.talentchat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

class LoginData{
    String username;
    String password;

    public LoginData(String username, String password){
        this.password = password;
        this.username = username;
    }
}


public interface ApiService {

    //   기반 주소                     |  GET  |    Query     |
    //   http://192.168.0.29:3001/api/auth/me?x-access-token=[String data]
    @GET("auth/me")
    Call<ResponseBody> getFunc(@Header("x-access-token") String data);

    // 내 정보
    @GET("auth/me")
    Call<ResponseBody> getMe(@Header("x-access-token") String data);

    // 특정 유저 정보
    @GET("users/{id}")
    Call<ResponseBody> getUser(@Header("x-access-token") String data, @Path("id") String id);

    // 신청 매칭: 목록 확인
    @GET("info/my_request")
    Call<ResponseBody> getMyRequset(@Header("x-access-token") String data);

    // 카테고리
    @GET("category/{category}")
    Call<ResponseBody> getUsersByCategory(@Header("x-access-token") String data, @Path("category") String category);

    // 요청 추가(이름)
    @PUT("matching/{id}/apply") // 유저 이름으로 보냈으므로 이름으로 받는다
    Call<ResponseBody> reqApply(@Header("x-access-token") String data, @Path("id") String username);

    // 로그인
    @POST("auth/login")
    Call<ResponseBody> login(@Body LoginData data);

    // 받은 매칭: 요청 목록 확인
    @GET("info/my_response")
    Call<ResponseBody> getMyResponser(@Header("x-access-token") String data);

    // 신청 완료: 둘이 매칭 된 결과 목록
    @GET("info/my_matching")
    Call<ResponseBody> getMyMatching(@Header("x-access-token") String data);

    // 매칭 수락
    @PUT("matching/{id}/accept") // 유저 이름으로 보냈으므로 이름으로 받는다
    Call<ResponseBody> accept(@Header("x-access-token") String data, @Path("id") String username);

    // 매칭 거절
    @PUT("matching/{id}/reject") // 유저 이름으로 보냈으므로 이름으로 받는다
    Call<ResponseBody> reject(@Header("x-access-token") String data, @Path("id") String username);

    //
    //

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