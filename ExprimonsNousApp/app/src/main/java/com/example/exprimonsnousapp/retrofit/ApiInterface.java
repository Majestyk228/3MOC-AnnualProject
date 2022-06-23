package com.example.exprimonsnousapp.retrofit;

import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.models.UserLoginCreds;
import com.example.exprimonsnousapp.models.UserUpdateResponse;
import com.example.exprimonsnousapp.models.UserUpdatedInfos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;

public interface ApiInterface {
    //@GET("/user/login")
    @HTTP(method = "POST", path = "/user/login", hasBody = true)
    Call<UserCreds> getUserCreds(@Body UserLoginCreds userLoginCreds);

    //UPDATE USER INFOS
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "PUT", path = "/user/infos/update", hasBody = true)
    Call<UserUpdateResponse> updateUserInfo(@Body UserUpdatedInfos userUpdatedInfos);
}
