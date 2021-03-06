package levy.daniel.application.model.services.metier.message.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import levy.daniel.application.model.metier.greeter.Greeter;
import levy.daniel.application.model.services.metier.message.IMessageService;

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
	@Override
	public String getMessage() {
		
		/* Interroge le metier. */
		final Greeter greeter = new Greeter();
		
		final String message = greeter.sayHello();
		
		return message;
		
	} // Fin de getMessage().______________________________________________
	


} // FIN DE LA CLASSE MockMessageService.------------------------------------
