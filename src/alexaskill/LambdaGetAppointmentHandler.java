package alexaskill;

import java.util.HashSet;
import java.util.Set;


import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class LambdaGetAppointmentHandler extends SpeechletRequestStreamHandler {


	private static final Set<String> supportedApplicationIds = new HashSet<String>();
	
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds.add("amzn1.ask.skill.a9343718-8711-4f64-8daa-f1f7af143735");
    }

	
	 public LambdaGetAppointmentHandler() {
		super(new GetAppointmentSpeechlet(), supportedApplicationIds);
		// TODO Auto-generated constructor stub
	}
}