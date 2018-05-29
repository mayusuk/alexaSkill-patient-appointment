package alexaskill;

import java.io.IOException;

import retrofit.Response;


public class main {
	
	public static void main(String args[]) throws IOException {
		CallExternalApi api = new CallExternalApi();
		Response response = api.callGetAppointments();
		System.out.println(response.body());
	}

}
