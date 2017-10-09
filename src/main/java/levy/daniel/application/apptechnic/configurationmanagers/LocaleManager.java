package levy.daniel.application.apptechnic.configurationmanagers;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class LocaleManager :<br/>
 * <ul>
 * <li>Classe de CONFIGURATION (finale avec des méthodes statiques)
 * chargée de fournir une Locale unique à toute l'application.</li>>
 * </ul<
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Locale, Locale("fr", "FR") en France, <br/>
 * instancier une Locale, SINGLETON, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 9 août 2017
 *
 */
public final class LocaleManager {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_LOCALEMANAGER : String :<br/>
	 * "Classe LocaleManager".<br/>
	 */
	public static final String CLASSE_LOCALEMANAGER 
		= "Classe LocaleManager";

		
	/**
	 * localeApplication : Locale :<br/>
	 * <ul>
	 * <li><b>SINGLETON</b> localeApplication à utiliser 
	 * dans toute l'application.</li>
	 * <li>L'application doit être internationalisable.</li>
	 * </ul>
	 */
	private static Locale localeApplication;
	
	
	/**
	 * localesDisponibles : Map&lt;String,Locale&gt; :<br/>
	 * <ul>
	 * Map contenant les Locales disponibles dans l'application avec :
	 * <li>String : nom de la Locale affiché dans l'IHM comme 
	 * "français (France)" ou "anglais (Etats-Unis)".</li>
	 * <li>Locale : la Locale correspondante comme 
	 * Locale("fr", "FR") ou Locale("en", "US").</li>
	 * </ul>
	 */
	private static Map<String, Locale> localesDisponibles 
		= new ConcurrentHashMap<String, Locale>();
	

static {
	
	localesDisponibles.put("français (France)", new Locale("fr", "FR"));
	localesDisponibles.put("anglais (Etats-Unis)", new Locale("en", "US"));
}


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(LocaleManager.class);

	// *************************METHODES************************************/
	

	
	 /**
	 * method CONSTRUCTEUR LocaleManager() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private LocaleManager() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * method recupererLocaleIHM(
	 * String pLocaleString) :<br/>
	 * <ul>
	 * <li>Récupère dans l'IHM une String comme "français (France)" 
	 * et retourne la Locale correspondante.</li>
	 * <li>pLocaleString doit appartenir aux clés de la 
	 * Map 'localesDisponibles'.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si pLocaleString est blank.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si pLocaleString  n'appartient pas aux locales disponibles.</li>
	 * </ul>
	 *
	 * @param pLocaleString : String : 
	 * String appartenant aux clés de la Map 'localesDisponibles'.<br/>
	 * 
	 * @return : Locale.<br/>
	 */
	public static Locale recupererLocaleIHM(
			final String pLocaleString) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne la Locale de la plateforme par défaut 
			 * si pLocaleString est blank. */
			if (StringUtils.isBlank(pLocaleString)) {
				return Locale.getDefault();
			}
			
			if (localesDisponibles.containsKey(pLocaleString)) {
				return localesDisponibles.get(pLocaleString);
			}
			
			/* retourne la Locale de la plateforme par défaut 
			 * si pLocaleString  n'appartient pas aux 
			 * locales disponibles. */
			return Locale.getDefault();
			
		} // Fin de synchronized._____________________________
		
	} // Fin de recupererLocaleIHM(...).___________________________________
	

	
	/**
	 * method recupererLocaleBase(
	 * String pLangue
	 * , String pPays) :<br/>
	 * <ul>
	 * <li>Prend en paramètre (récupère dans la base par exemple) 
	 * un couple de  Strings [langue, pays] 
	 * comme ["fr", "FR"] ou ["en", "US"] 
	 * et instancie la Locale correspondante.</li>
	 * <li>Retourne la Locale instanciée si elle appartient 
	 * aux Locales disponibles dans l'application 
	 * (localesDisponibles).</li>
	 * <li>La locale instanciée doit appartenir aux valeurs de la 
	 * Map 'localesDisponibles'.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si pLangue ou pPays est blank.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si la locale instanciée n'appartient pas 
	 * aux locales disponibles.</li>
	 * <li>retourne la locale de la plateforme par défaut si 
	 * [pLangue, pPays] ne correspond à aucune Locale.</li>
	 * </ul>
	 *
	 * @param pLangue : String : langue de la Locale comme 
	 * "fr", "en", ...<br/>
	 * @param pPays : String : pays de la Locale 
	 * comme "FR", "US", ...<br/>
	 * 
	 * @return : Locale.<br/>
	 */
	public static Locale recupererLocaleBase(
			final String pLangue
				, final String pPays) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne la Locale de la plateforme par défaut 
			 * si pLocaleString est blank. */
			if (StringUtils.isBlank(pLangue) 
					|| StringUtils.isBlank(pPays)) {
				return Locale.getDefault();
			}
			
			Locale locale = null;
			
			try {
				
				locale = new Locale(pLangue, pPays);
				
				/* retourne la Locale de la plateforme par défaut 
				 * si pLocaleString  n'appartient pas aux 
				 * locales disponibles. */
				if (!localesDisponibles.values().contains(locale)) {
					locale = Locale.getDefault();
				}
				
			}
			catch (Exception e) {
				
				/* retourne la locale de la plateforme par défaut 
				 * si [pLangue, pPays] ne correspond à aucune Locale. */
				locale = Locale.getDefault();
				
			}
							
			return locale;
			
		} // Fin de synchronized._____________________________
		
	} // Fin de recupererLocaleBase(...).__________________________________
	
	
	
	/**
	 * method getLangageLocaleApplication() :<br/>
	 * <ul>
	 * <li>Retourne le langage (fr, en, ...) de localeApplication.</li>
	 * </ul>
	 *
	 * @return : String : Le langage (fr, en, ...) 
	 * de localeApplication.<br/>
	 */
	public static String getLangageLocaleApplication() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			return localeApplication.getLanguage();
		} // Fin de synchronized._____________________________
		
	} // Fin de getLangageLocaleApplication()._____________________________


	
	/**
	 * method getCountryLocaleApplication() :<br/>
	 * <ul>
	 * <li>Retourne le country (FR, US, ...) de localeApplication.</li>
	 * </ul>
	 *
	 * @return : String : le country (FR, US, ...) 
	 * de localeApplication.<br/>
	 */
	public static String getCountryLocaleApplication() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			return localeApplication.getCountry();
		} // Fin de synchronized._____________________________
		
	} // Fin de getCountryLocaleApplication()._____________________________
	

	
	/**
	 * method getLocaleParDefaut() :<br/>
	 * <ul>
	 * <li>Retourne la Locale retournée par la JVM en fonction 
	 * de la position de la machine dans le monde.</li>
	 * <li>Locale("fr", "FR") en France.</li>
	 * </ul>
	 *
	 * @return : Locale : Locale("fr", "FR") en France.<br/>
	 */
	public static Locale getLocaleParDefaut() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			return Locale.getDefault();
		} // Fin de synchronized._____________________________
		
	} // Fin de getLocaleParDefaut().______________________________________


		
	/**
	 * method getLocalesDisponibles() :<br/>
	 *<ul>
	 * Getter de la Map contenant les Locales disponibles 
	 * dans l'application avec :
	 * <li>String : nom de la Locale affiché dans l'IHM 
	 * comme "français (France)" ou "anglais (Etats-Unis)".</li>
	 * <li>Locale : la Locale correspondante
	 *  comme Locale("fr", "FR") ou Locale("en", "US").</li>
	 * </ul>
	 *
	 * @return localesDisponibles : Map<String,Locale>.<br/>
	 */
	public static Map<String, Locale> getLocalesDisponibles() {
	
		return localesDisponibles;
		
	} // Fin de getLocalesDisponibles().___________________________________



	/**
	 * method getLocaleApplication() :<br/>
	 * <ul>
	 * <li>Getter du <b>SINGLETON</b> localeApplication à utiliser 
	 * dans toute l'application.</li>
	 * <li>L'application doit être internationalisable.</li>
	 * </ul>
	 *
	 * @return localeApplication : Locale.<br/>
	 */
	public static Locale getLocaleApplication() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* N'instancie localeApplication que 
			 * si elle est null (SINGLETON). */
			if (localeApplication == null) {
				localeApplication = recupererLocaleIHM(null);
			}
			
			return localeApplication;
			
		} // Fin de synchronized._____________________________
		
	} // Fin de getLocaleApplication().____________________________________
	

	
	/**
	* method setLocaleApplication(
	* Locale pLocaleApplication) :<br/>
	* <ul>
	 * <li>Setter du <b>SINGLETON</b> localeApplication à utiliser 
	 * dans toute l'application.</li>
	 * <li>L'application doit être internationalisable.</li>
	 * </ul>
	*
	* @param pLocaleApplication : Locale : 
	* valeur à passer à localeApplication.<br/>
	*/
	public static void setLocaleApplication(
			final Locale pLocaleApplication) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			localeApplication = pLocaleApplication;
		} // Fin de synchronized._____________________________
		
	} // Fin de setLocaleApplication(
	// Locale pLocaleApplication)._________________________________________
	
	
	
	
} // FIN DE LA CLASSE LocaleManager.-----------------------------------------
