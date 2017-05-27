package bemo.moviechat.network;

import retrofit2.Response;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class RetrofitSuccessEvent {

    private Response response;
    private String tag;

    public RetrofitSuccessEvent(Response response, String tag) {
        try {
            this.response = response;
            this.tag = tag;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}