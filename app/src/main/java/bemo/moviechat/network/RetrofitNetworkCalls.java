package bemo.moviechat.network;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import bemo.moviechat.Const;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class RetrofitNetworkCalls {

        public static void makeRetrofitCall(final Context context, Call call, final String tag){
        try {
            final EventBus eventBus = EventBus.getDefault();

            for (int i = 0; i < call.request().headers().size(); i++) {
                Log.d(Const.TAG, "hello im the header " + call.request().headers().name(i));
                Log.d(Const.TAG, "hello im the header " + call.request().headers().value(i));
            }

            Log.d(Const.TAG, "hitting it with api: " + call.request().toString());

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {

                    if (response.isSuccessful()) {
                        eventBus.post(new RetrofitSuccessEvent(response, tag));
                    } else {
                        try {
                            eventBus.post(new RetrofitErrorEvent(tag));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    eventBus.post(new RetrofitErrorEvent(tag));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
