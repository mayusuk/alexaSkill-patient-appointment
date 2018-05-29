package alexaskill;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.LinkAccountCard;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import dto.PatientReminder;

/**
* This sample shows how to create a simple speechlet for handling speechlet requests.
*/
public class GetAppointmentSpeechlet implements Speechlet {
	private static final Logger log = LoggerFactory.getLogger(GetAppointmentSpeechlet.class);
	
	@Override
	public void onSessionStarted(final SessionStartedRequest request, final Session session)
	        throws SpeechletException {
	    log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
	            session.getSessionId());
	    // any initialization logic goes here
	}
	
	@Override
	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
	        throws SpeechletException {
	    log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
	            session.getSessionId());
	    return getWelcomeResponse();
	}
	
	@Override
	public SpeechletResponse onIntent(final IntentRequest request, final Session session)
	        throws SpeechletException {
	    log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
	            session.getSessionId());
	
	    Intent intent = request.getIntent();
	    String intentName = (intent != null) ? intent.getName() : null;
	    
	    String access_token = null;
	    if(session.getUser() != null) {
	    	if (session.getUser().getAccessToken() == null){
	    		String speechText = "You have to link your amazon account to patient portal";
	    	    // Create the plain text output.
	    	    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
	    	    speech.setText(speechText);
	    	    
	    	    LinkAccountCard  linkAccountCard = new LinkAccountCard();
	    	    linkAccountCard.setTitle("Link your amazon account");
	    	
	    	    return SpeechletResponse.newTellResponse(speech, linkAccountCard);
	    	}
	    	else {
	    		access_token = "Bearer ".concat(session.getUser().getAccessToken());
	    	}
	    }
	
	    if ("GetappointmentIntent".equals(intentName)) {
	        try {
				return getHelloResponse(access_token);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } else if ("AMAZON.HelpIntent".equals(intentName)) {
	        return getHelpResponse();
	    } else {
	        throw new SpeechletException("Invalid Intent");
	    }
		return null;
	}
	
	@Override
	public void onSessionEnded(final SessionEndedRequest request, final Session session)
	        throws SpeechletException {
	    log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
	            session.getSessionId());
	    // any cleanup logic goes here
	}
	
	/**
	 * Creates and returns a {@code SpeechletResponse} with a welcome message.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getWelcomeResponse() {
	    String speechText = "Welcome to the Alexa Skills Kit, you can say hello";
	
	    // Create the Simple card content.
	    SimpleCard card = new SimpleCard();
	    card.setTitle("HelloWorld");
	    card.setContent(speechText);
	
	    // Create the plain text output.
	    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
	    speech.setText(speechText);
	
	    // Create reprompt
	    Reprompt reprompt = new Reprompt();
	    reprompt.setOutputSpeech(speech);
	
	    return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}
	
	/**
	 * Creates a {@code SpeechletResponse} for the hello intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 * @throws IOException 
	 */
	private SpeechletResponse getHelloResponse(String access_token) throws IOException {
		
		CallExternalApi api = new CallExternalApi();
		Collection<PatientReminder> patientReminders= ((Collection<PatientReminder>)api.callGetAppointments(access_token).body());
		String speechText = null;
		if( patientReminders == null || patientReminders.isEmpty()) {
        	speechText = "You dont have any appointments";
        }
        else {
        	
        	int number = patientReminders.size();
            PatientReminder patientReminder = null;
    		for (PatientReminder test : patientReminders){
    			patientReminder = test;
    		}
    	    speechText = "Hello john, you have " + number + "appointments." + patientReminder == null ? " " :patientReminder.getDescription();

		}
			
	    // Create the Simple card content.
	    SimpleCard card = new SimpleCard();
	    card.setTitle("HelloWorld");
	    card.setContent(speechText);
	
	    // Create the plain text output.
	    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
	    speech.setText(speechText);
	
	    return SpeechletResponse.newTellResponse(speech, card);
	}
	
	/**
	 * Creates a {@code SpeechletResponse} for the help intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getHelpResponse() {
	    String speechText = "You can say hello to me!";
	
	    // Create the Simple card content.
	    SimpleCard card = new SimpleCard();
	    card.setTitle("HelloWorld");
	    card.setContent(speechText);
	
	    // Create the plain text output.
	    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
	    speech.setText(speechText);
	
	    // Create reprompt
	    Reprompt reprompt = new Reprompt();
	    reprompt.setOutputSpeech(speech);
	
	    return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}
}
