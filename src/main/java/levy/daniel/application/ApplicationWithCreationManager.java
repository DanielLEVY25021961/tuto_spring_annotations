package levy.daniel.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import levy.daniel.application.model.services.metier.message.impl.MockMessageService;


/**
 * class ApplicationWithCreationManager :<br/>
 * Lanceur de l'application spring avec parametrage de spring par un fichier
 * (applicationContext.xml
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
 * @author oric
 * @version 1.0
 * @since 17 oct. 2017
 *
 */
@Component
public class ApplicationWithCreationManager {

	// ************************ATTRIBUTS************************************/

	/**
	 * CONFIG_PATH : String :<br/>
	 * "classpath*:applicationContext.xml".<br/>
	 */
	private static final String CONFIG_PATH 
		= "classpath*:applicationContext.xml";

	

	// *************************METHODES************************************/
	
	
	/**
	 * method afficher() :<br/>
	 * .<br/>
	 * <br/>
	 */
	public void afficher() {
		System.out.println("Hello - applicationContext.xml");
	} // Fin de afficher().________________________________________________
	
	

	/**
	 * method main(
	 * String[] pArgs) :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 */
	public static void main(final String[] pArgs) {

		// Création l'application context spring avec le fichier de configuration
		// applicatioContext.xml
		final AbstractApplicationContext context 
			= new ClassPathXmlApplicationContext(CONFIG_PATH);

		// récupération de l'instance ApplicationWithCreationManager
		final ApplicationWithCreationManager applicationWithCreationManager = context
				.getBean(ApplicationWithCreationManager.class);
		
		/**/
		applicationWithCreationManager.afficher();

		// récupération de l'instance MockMessageService
		final MockMessageService mockMessageService 
			= context.getBean(MockMessageService.class);
		
		System.out.println(
				"Hello from MockMessageService " 
						+ mockMessageService.getMessage());
		
		/* Clôture du contexte, sinon, fuite de mémoire. */
		context.close();
		
	} // Fin de main(...)._________________________________________________
	
	

} // FIN DE LA CLASSE ApplicationWithCreationManager.------------------------
