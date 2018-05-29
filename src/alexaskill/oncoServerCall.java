package alexaskill;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;

import java.util.Collection;

import dto.*;


public interface oncoServerCall {
//@Headers(value = {"Authorization: Basic anNtaXRoOm9XYkIwdHRleXlKZFQyWHJScUZYSVRCZUtCUWVBZEJYWTJRanhOTC9xb0E9"})

    @GET("patients/{id}/reminders")
    Call<Collection<PatientReminder>> getAppointments(@Header("Authorization") String access_token, @Path("id") Long personId);

}
