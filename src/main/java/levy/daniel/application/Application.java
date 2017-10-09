package levy.daniel.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalTime;

import levy.daniel.application.model.metier.greeter.Greeter;


/**
 * class Application :<br/>
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
public final class Application {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Application.class);
	
	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR Application() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private Application() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method main() :<br/>
	 * Point d'entrée de l'application.<br/>
	 * <br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 */
	public static void main(
			final String[] pArgs) {
		
		final LocalTime currentTime = new LocalTime();
		System.out.println("The current local time is: " + currentTime);
		
		final Greeter greeter = new Greeter();
		System.out.println(greeter.sayHello());

	} // Fin de main(...)._________________________________________________
	
	

} // FIN DE LA CLASSE Application.-------------------------------------------
