package levy.daniel.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import levy.daniel.application.model.services.metier.message.impl.MockMessageService;

/**
 * Lanceur de l'application spring avec parametrage de spring par un fichier
 * (applicationContext.xml
 *
 * @author oric
 *
 */
@Component
public class ApplicationWithCreationManager {

	private static final String CONFIG_PATH = "classpath*:applicationContext.xml";

	void afficher() {
		System.out.println("Hello - applicationContext.xml");
	}

	public static void main(final String[] args) {

		// Création l'application context spring avec le fichier de configuration
		// applicatioContext.xml
		final ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_PATH);

		// récupération de l'instance ApplicationWithCreationManager
		final ApplicationWithCreationManager applicationWithCreationManager = context
				.getBean(ApplicationWithCreationManager.class);
		applicationWithCreationManager.afficher();

		// récupération de l'instance MockMessageService
		final MockMessageService mockMessageService = context.getBean(MockMessageService.class);
		System.out.println("Hello from MockMessageService " + mockMessageService.getMessage());
	}

}
