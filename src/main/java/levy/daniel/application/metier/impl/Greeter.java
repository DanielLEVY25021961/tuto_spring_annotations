package levy.daniel.application.metier.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class Greeter :<br/>
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
public class Greeter {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Greeter.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR Greeter() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public Greeter() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	
	/**
	 * method sayHello() :<br/>
	 * "Hello world!".<br/>
	 * <br/>
	 *
	 * @return : String : "Hello world!".<br/>
	 */
	public final String sayHello() {
		return "Hello world!";
	} // Fin de sayHello().________________________________________________
	
	
	
} // FIN DE LA CLASSE Greeter.-----------------------------------------------
