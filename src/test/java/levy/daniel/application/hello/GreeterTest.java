package levy.daniel.application.hello;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.metier.greeter.Greeter;

/**
 * class GreeterTest :<br/>
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
public class GreeterTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * greeter : Greeter :<br/>
	 * .<br/>
	 */
	private final transient Greeter greeter = new Greeter();

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(GreeterTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GreeterTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public GreeterTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testSayHello() :<br/>
	 * .<br/>
	 * <br/>
	 */
	@Test
	public void testSayHello() {
		
		assertThat("Doit contenir Hello : "
				, this.greeter.sayHello()
					, containsString("Hello"));
		
	} // Fin de testSayHello().____________________________________________


	
} // FIN DE LA CLASSE GreeterTest.-------------------------------------------
