package alexaskill;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.PatientReminder;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

// http://localhost:8080/RESTfulExample/json/product/post


public class CallExternalApi {

    String method;
    Object input;
    String api;
    Retrofit retrofit;


    public CallExternalApi() {
                retrofit = new Retrofit.Builder()
                .baseUrl("https://server-e2e.hdev.in")
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                .build();
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getInput() {
        return input;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
    
    public retrofit.Response callGetAppointments(String access_token) throws IOException {
		return retrofit.create(oncoServerCall.class).getAppointments(access_token, 1000L).execute();

    }
}