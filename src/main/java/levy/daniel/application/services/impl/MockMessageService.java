package levy.daniel.application.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import levy.daniel.application.metier.impl.Greeter;
import levy.daniel.application.services.IMessageService;

/**
 * class MockMessageService :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 10 août 2017
 *
 */
@Component
public class MockMessageService implements IMessageService {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(MockMessageService.class);
	
	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR MockMessageService() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public MockMessageService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	
	/**
	 * {@inheritDoc}
	 */
	public String getMessage() {
		
		/* Interroge le metier. */
		final Greeter greeter = new Greeter();
		
		final String message = greeter.sayHello();
		
		return message;
		
	} // Fin de getMessage().______________________________________________
	


} // FIN DE LA CLASSE MockMessageService.------------------------------------
