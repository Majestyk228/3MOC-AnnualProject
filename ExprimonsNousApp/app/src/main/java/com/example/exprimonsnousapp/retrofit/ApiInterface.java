package com.example.exprimonsnousapp.retrofit;

import com.example.exprimonsnousapp.models.IdCommunity;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.models.UserLoginCreds;
import com.example.exprimonsnousapp.models.UserUpdateResponse;
import com.example.exprimonsnousapp.models.UserUpdatedInfos;
import com.example.exprimonsnousapp.models.Vote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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

    // GET LISTS OF VOTES FROM A COMMUNITY
    @HTTP(method = "POST", path = "/vote/voteListAndroid", hasBody = true)
    Call<List<Vote>> getVotesFromCommunity(@Body IdCommunity idCommunity);
}
