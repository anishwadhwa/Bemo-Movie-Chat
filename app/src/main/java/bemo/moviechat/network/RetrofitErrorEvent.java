package bemo.moviechat.network;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class RetrofitErrorEvent {

    private String tag;

    public RetrofitErrorEvent(String tag) {
        try {
            this.tag = tag;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}