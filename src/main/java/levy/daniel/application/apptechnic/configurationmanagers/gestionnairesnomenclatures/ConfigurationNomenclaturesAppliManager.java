package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesnomenclatures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierRepertoireRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideRunTimeException;


/**
 * class ConfigurationNomenclaturesAppliManager :<br/>
 * Classe UTILITAIRE 
 * chargée de gérer la configuration des 
 * NOMENCLATURES DIVERSES DE L'APPLICATION 
 * (attribut TypeErreur dans les contrôles
 * , attribut suiteDonnee dans les controles...).<br/>
 * Met à disposition de l'ensemble de l'application 
 * des Singletons.<br/>
 * <br/>
 * <ul>
 * <li>La méthode getCheminNomenclaturesAppliUtf8 fournit un Singleton 
 * du chemin vers les nomenclatures encodées en UTF-8 
 * des attributs à nomenclature 
 * de l'application (typeErreur pour les contrôles, ...).</li><br/>
 * <li>Les méthodes getNomNomenclatureXXX fournissent un singleton  
 * du nom du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans l'application.</li><br/>
 * <li>Les méthodes getFichierNomenclatureXXX fournissent un singleton  
 * du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans l'application.</li><br/>
 * <li>La méthode getSetClesNomenclatureUtf8(File pNomenclature) 
 * fournit le Singleton du Set des clés 
 * d'une nomenclature encodée en UTF8.</li><br/>
 * <li>La méthode getMapNomenclatureUtf8(File pNomenclature) 
 * fournit le Singleton de la SortedMap&lt;Integer, String&gt; 
 * d'une nomenclature encodée en UTF8.</li><br/>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Singleton,
 * lire un fichier en UTF-8, lire nomenclature,<br/>
 * Set de clés d'une nomenclature,<br/> 
 * nomenclature sous forme de SortedMap<Integer, String>,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 29 mai 2016
 *
 */
public final class ConfigurationNomenclaturesAppliManager {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_CONFIGURATION_NOMENCLATURES_APPLI : String :<br/>
	 * "Classe ConfigurationNomenclaturesAppliManager".<br/>
	 */
	public static final String CLASSE_CONFIGURATION_NOMENCLATURES_APPLI 
		= "Classe ConfigurationNomenclaturesAppliManager";
	
	
	/**
	 * METHODE_GET_CHEMIN_NOMENCLATURES_APPLI : String :<br/>
	 * "Méthode getCheminNomenclaturesAppliUtf8".<br/>
	 */
	public static final String METHODE_GET_CHEMIN_NOMENCLATURES_APPLI 
		= "Méthode getCheminNomenclaturesAppliUtf8";

	
	/**
	 * METHODE_GET_NOM_NOMENCLATURE_APPLI_TYPE_ERREUR : String :<br/>
	 * "Méthode getNomNomenclatureAppliTypeErreur()".<br/>
	 */
	public static final String METHODE_GET_NOM_NOMENCLATURE_APPLI_TYPE_ERREUR 
		= "Méthode getNomNomenclatureAppliTypeErreur()";

	
	/**
	 * METHODE_GET_NOM_NOMENCLATURE_APPLI_SUITE_DONNEE : String :<br/>
	 * "Méthode getNomNomenclatureAppliSuiteDonnee()".<br/>
	 */
	public static final String METHODE_GET_NOM_NOMENCLATURE_APPLI_SUITE_DONNEE 
		= "Méthode getNomNomenclatureAppliSuiteDonnee()";

	
	/**
	 * METHODE_GETSETCLESNOMENCLATUREUTF8 : String :<br/>
	 * "Méthode getSetClesNomenclatureUtf8(Set<Integer> pSet, File pNomenclature)".<br/>
	 */
	public static final String METHODE_GETSETCLESNOMENCLATUREUTF8 
	= "Méthode getSetClesNomenclatureUtf8(Set<Integer> pSet, File pNomenclature)";
	
	
	/**
	 * METHODE_REMPLIRSETCLESNOMENCLATUREUTF8 : String :<br/>
	 * "Méthode remplirSetClesNomenclatureUtf8(File pNomenclature)".<br/>
	 */
	public static final String METHODE_REMPLIRSETCLESNOMENCLATUREUTF8 
		= "Méthode remplirSetClesNomenclatureUtf8(File pNomenclature)";
	
	
	/**
	 * METHODE_GETMAPNOMENCLATUREUTF8 : String :<br/>
	 * "Méthode getMapNomenclatureUtf8(SortedMap<Integer, String> pMap, File pNomenclature)".<br/>
	 */
	public static final String METHODE_GETMAPNOMENCLATUREUTF8 
	= "Méthode getMapNomenclatureUtf8(SortedMap<Integer, String> pMap, File pNomenclature)";

	
	/**
	 * METHODE_REMPLIRMAPNOMENCLATUREUTF8 : String :<br/>
	 * "Méthode remplirMapNomenclatureUtf8(File pNomenclature)".<br/>
	 */
	public static final String METHODE_REMPLIRMAPNOMENCLATUREUTF8 
		= "Méthode remplirMapNomenclatureUtf8(File pNomenclature)";
	
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * BOM_UTF : char :<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * SEP_PV : String :<br/>
	 * Séparateur pour les CSV ";".<br/>
	 */
	public static final String SEP_PV = ";";

    
	/**
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
	
	
	/**
	 * UNDERSCORE : String :<br/>
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";


	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	
	//*****************************************************************/
	//**************************** LOCALE *****************************/
	//*****************************************************************/
	/**
	 * LOCALE_FR : Locale : <br/>
	 * Locale France.<br/>
	 */
	public static final Locale LOCALE_FR = new Locale("fr", "FR");
	
	
	//*****************************************************************/
	//**************************** CHARSET ****************************/
	//*****************************************************************/

	/**
	 * CHARSET_UTF8 : Charset :<br/>
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	public static final Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");


	//*****************************************************************/
	//**************************** PATTERN ****************************/
	//*****************************************************************/
	
	/**
	 * PATTERN_SEPARATEUR_CSV : Pattern :<br/>
	 * Pattern pour casser les lignes csv avec séparateur ';' 
	 * en String[] tokens.<br/>
	 */
	public static final Pattern PATTERN_SEPARATEUR_CSV = Pattern.compile(";");
	

	/**
	 * PROBLEME_GRAVE : String :<br/>
	 * "PROBLEME GRAVE".<br/>
	 */
	public static final String PROBLEME_GRAVE = "PROBLEME GRAVE";
	

	
	// ******************************************************************
	// NOMENCLATURES.****************************************************
	// ******************************************************************
		
	// CHEMIN DES NOMENCLATURES DE L'APPLI.*********************
	
	/**
	 * cheminNomenclaturesAppliUtf8 : String :<br/>
	 * <ul>
	 * <li>Chemin du répertoire <b>EXTERNE</b> (hors classpath) 
	 * des nomenclatures en UTF-8 des attributs à nomenclature 
	 * de l'application.</li>
	 * <li>stocké dans application.properties.</li>
	 * <li>"D:/Donnees/eclipse/eclipseworkspace_neon/
	 * tuto_maven_sonatype/ressources_externes/nomenclatures/utf8".</li>
	 * <li>Clé = "application.repertoire.ressources
	 * .nomenclatures.chemin.appli.utf8".</li>
	 */
	private static transient String cheminNomenclaturesAppliUtf8;
	
	
	// Unités.*********************
	
	/**
	 * nomNomenclatureUnites : String :<br/>
	 * <ul>
	 * <li>Nom du fichier UTF-8 csv de nomenclature 
	 * pour les unités (métriques ou impériales).</li>
	 * <li>stocké dans application.properties.</li>
	 * <li>"nomenclature_unites_utf8.csv"</li>
	 * <li>Clé = "application.repertoire.ressources.nomenclature.nom.unites".</li>
	 * </ul>
	 */
	private static transient String nomNomenclatureUnites;
	
	// TypeErreur.*********************
	
	/**
	 * nomNomenclatureAppliTypeErreur : String :<br/>
	 * <ul>
	 * <li>Nom du fichier UTF-8 csv de nomenclature pour typeErreur.</li>
	 * <li>stocké dans application.properties.</li>
	 * <li>"Nomenclature_Type_Erreur_UTF8.csv".</li>
	 * <li>Clé = "application.repertoire.ressources.nomenclature.appli.typeerreur".</li>
	 * </ul>
	 */
	private static transient String nomNomenclatureAppliTypeErreur;

	
	/**
	 * fichierNomenclatureAppliTypeErreurUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le typeErreur
	 * dans les contrôles de l'application.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\appli\\
	 * Nomenclature_Type_Erreur_UTF8.csv".<br/>
	 */
	private static transient File fichierNomenclatureAppliTypeErreurUtf8;


	/**
	 * setClesNomenclatureTypeErreur : Set&lt;Integer&gt; :<br/>
	 * Set des clés de la nomenclature de typeErreur pour les contrôles.<br/>
	 */
	private static transient Set<Integer> setClesNomenclatureTypeErreur;

		
	/**
	 * mapNomenclatureTypeErreur : SortedMap&lt;Integer,String&gt; :<br/>
	 * Map de la nomenclature de typeErreur pour les contrôles.<br/>
	 */
	private static transient SortedMap<Integer, String> mapNomenclatureTypeErreur;

	
	// SuiteDonnee.*********************
	
	/**
	 * nomNomenclatureAppliSuiteDonnee : String :<br/>
	 * Nom du fichier de nomenclature en UTF-8 pour suiteDonnee
	 * stocké dans application.properties.<br/>
	 * "Nomenclature_Suite_Donnee_UTF8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclature.appli.suitedonnee".<br/>
	 */
	private static transient String nomNomenclatureAppliSuiteDonnee;

	
	/**
	 * fichierNomenclatureAppliSuiteDonneeUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le suiteDonnee
	 * dans les contrôles de l'application.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\appli\\
	 * Nomenclature_Suite_Donnee_UTF8.csv".<br/>
	 */
	private static transient File fichierNomenclatureAppliSuiteDonneeUtf8;


	/**
	 * setClesNomenclatureSuiteDonnee : Set&lt;Integer&gt; :<br/>
	 * Set des clés de la nomenclature de suiteDonnee pour les contrôles.<br/>
	 */
	private static transient Set<Integer> setClesNomenclatureSuiteDonnee;

		
	/**
	 * mapNomenclatureSuiteDonnee : SortedMap&lt;Integer,String&gt; :<br/>
	 * Map de la nomenclature de suiteDonnee pour les contrôles.<br/>
	 */
	private static transient SortedMap<Integer, String> mapNomenclatureSuiteDonnee;
	

	
	/**
	 * rapportConfigurationCsv : String :<br/>
	 * Rapport du chargement de la configuration au format csv.<br/>
	 * Le rapport est null si il n'y a eu aucun 
	 * problème d'initialisation de l'application.<br/>
	 */
	private static transient String rapportConfigurationCsv;

	
	/**
	 * messageIndividuelRapport : String :<br/>
	 * Message pour le Rapport du chargement de la configuration au format csv 
	 * généré par chaque méthode individuellement.<br/>
	 */
	private static transient String messageIndividuelRapport;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ConfigurationNomenclaturesAppliManager.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ConfigurationNomenclaturesAppliManager() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 * <br/>
	 */
	private ConfigurationNomenclaturesAppliManager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * method getCheminNomenclaturesAppliUtf8() :<br/>
	 * Getter du Chemin du REPERTOIRE <b>EXTERNE</b> (hors classpath) 
	 * des nomenclatures en UTF-8 des attributs pour l'application.<br/>
	 * Ce chemin du REPERTOIRE des nomenclatures en UTF-8 est indiqué 
	 * dans application.properties par le Centre Serveur.<br/>
	 * "D:/Donnees/eclipse/eclipseworkspace_neon/tuto_maven_sonatype/
	 * ressources_externes/nomenclatures/utf8".<br/>
	 * <ol>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirCheminNomenclaturesAppliUtf8EnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * </ol>
	 * Clé : "ressourcesexternes.nomenclatures.utf8".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesAppliUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesAppliUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return cheminNomenclaturesAppliUtf8 : String.<br/>
	 * @throws Exception 
	 */
	public static String getCheminNomenclaturesAppliUtf8() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (cheminNomenclaturesAppliUtf8 == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleCheminNomenclaturesAppliUtf8());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_CHEMIN_NOMENCLATURES_APPLI,
								fournirCleCheminNomenclaturesAppliUtf8(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							cheminNomenclaturesAppliUtf8 
								= fournirCheminNomenclaturesAppliUtf8EnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							cheminNomenclaturesAppliUtf8 = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
									METHODE_GET_CHEMIN_NOMENCLATURES_APPLI,
								fournirCleCheminNomenclaturesAppliUtf8(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						cheminNomenclaturesAppliUtf8 
							= fournirCheminNomenclaturesAppliUtf8EnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					cheminNomenclaturesAppliUtf8 
						= fournirCheminNomenclaturesAppliUtf8EnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (cheminNomenclaturesAppliUtf8 == null)._________

			return cheminNomenclaturesAppliUtf8;

		} // Fin de synchronized.________________________________________

	} // Fin de getCheminNomenclaturesAppliUtf8().___________________________

	
	
	/**
	 * method fournirCleCheminNomenclaturesAppliUtf8() :<br/>
	 * clé du chemin des chemins des nomenclatures en UTF-8 
	 * des attributs de l'application dans 
	 * application_fr_FR.properties.<br/>
	 * "D:/Donnees/eclipse/eclipseworkspace_neon/tuto_maven_sonatype/
	 * ressources_externes/nomenclatures/utf8".<br/>
	 * Clé = "ressourcesexternes.nomenclatures.utf8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "ressourcesexternes.nomenclatures.utf8".<br/>
	 */
	private static String fournirCleCheminNomenclaturesAppliUtf8() {
		return "ressourcesexternes.nomenclatures.utf8";
	} // Fin de fournirCleCheminNomenclaturesAppliUtf8().__________________
	

	
	/**
	 * method fournirCheminNomenclaturesAppliUtf8EnDur() :<br/>
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour chemins des nomenclatures en UTF-8 
	 * des HIT.<br/>
	 * <br/>
	 * "D:/Donnees/eclipse/eclipseworkspace_neon/tuto_maven_sonatype/
	 * ressources_externes/nomenclatures/utf8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "D:/Donnees/eclipse/eclipseworkspace_neon/tuto_maven_sonatype/
	 * ressources_externes/nomenclatures/utf8".<br/>
	 */
	private static String fournirCheminNomenclaturesAppliUtf8EnDur() {
		return "D:/Donnees/eclipse/eclipseworkspace_neon/"
				+ "tuto_maven_sonatype/ressources_externes/"
				+ "nomenclatures/utf8";
	} // Fin de fournirCheminNomenclaturesAppliUtf8EnDur().__________________
	

	// typeErreur **************************
	
	/**
	 * method getNomNomenclatureAppliTypeErreur() :<br/>
	 * Getter du Nom du fichier de nomenclature en UTF-8
	 * de l'attribut typeErreur 
	 * pour les contrôles de l'application 
	 * stocké dans application.properties.<br/>
	 * "Nomenclature_Type_Erreur_UTF8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureAppliTypeErreurEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclature.appli.typeerreur".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureAppliTypeErreurEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureAppliTypeErreurEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureAppliTypeErreur : String.<br/>
	 * @throws Exception 
	 */
	public static String getNomNomenclatureAppliTypeErreur() 
			throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureAppliTypeErreur == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureAppliTypeErreur());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOM_NOMENCLATURE_APPLI_TYPE_ERREUR,
								fournirCleNomNomenclatureAppliTypeErreur(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureAppliTypeErreur 
								= fournirNomNomenclatureAppliTypeErreurEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureAppliTypeErreur = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
									METHODE_GET_NOM_NOMENCLATURE_APPLI_TYPE_ERREUR,
								fournirCleNomNomenclatureAppliTypeErreur(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureAppliTypeErreur 
							= fournirNomNomenclatureAppliTypeErreurEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureAppliTypeErreur 
						= fournirNomNomenclatureAppliTypeErreurEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureAppliTypeErreur == null)._________

			return nomNomenclatureAppliTypeErreur;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureAppliTypeErreur()._______________________________


	
	/**
	 * method fournirCleNomNomenclatureAppliTypeErreur() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * de l'attribut typeErreur 
	 * dans l'application
	 * stockée dans application_fr_FR.properties.<br/>
	 * "Nomenclature_Type_Erreur_UTF8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclature.appli.typeerreur".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclature.appli.typeerreur".<br/>
	 */
	private static String fournirCleNomNomenclatureAppliTypeErreur() {
		return "application.repertoire.ressources.nomenclature.appli.typeerreur";
	} // Fin de fournirCleNomNomenclatureAppliTypeErreur().________________________
	

	
	/**
	 * method fournirNomNomenclatureAppliTypeErreurEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant l'attribut typeErreur
	 * dans l'application.<br/>
	 * <br/>
	 *
	 * @return : String : "Nomenclature_Type_Erreur_UTF8.csv".<br/>
	 */
	private static String fournirNomNomenclatureAppliTypeErreurEnDur() {
		return "Nomenclature_Type_Erreur_UTF8.csv";
	} // Fin de fournirNomNomenclatureAppliTypeErreurEnDur().______________________

	
	
	/**
	 * method getFichierNomenclatureAppliTypeErreurUtf8() :<br/>
	 * Fournit un Singleton du Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * l'attribut typeErreur
	 * dans l'application.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\appli\\
	 * Nomenclature_Type_Erreur_UTF8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureAppliTypeErreurUtf8.<br/>
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureAppliTypeErreurUtf8() 
														throws Exception {
				
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureAppliTypeErreurUtf8 == null) {
								
				fichierNomenclatureAppliTypeErreurUtf8 
				= new File(getCheminNomenclaturesAppliUtf8() 
						+ getNomNomenclatureAppliTypeErreur());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureAppliTypeErreurUtf8
						, "Méthode getFichierNomenclatureAppliTypeErreurUtf8()");
			}
			
			return fichierNomenclatureAppliTypeErreurUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureAppliTypeErreurUtf8()._______________
	

	// suiteDonnee **************************
	
	/**
	 * method getNomNomenclatureAppliSuiteDonnee() :<br/>
	 * Getter du Nom du fichier de nomenclature en UTF-8
	 * de l'attribut suiteDonnee 
	 * pour les contrôles de l'application 
	 * stocké dans application.properties.<br/>
	 * "Nomenclature_Suite_Donnee_UTF8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureAppliSuiteDonneeEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclature.appli.suitedonnee".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureAppliSuiteDonneeEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureAppliSuiteDonneeEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureAppliSuiteDonnee : String.<br/>
	 * @throws Exception 
	 */
	public static String getNomNomenclatureAppliSuiteDonnee() 
													throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureAppliSuiteDonnee == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureAppliSuiteDonnee());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOM_NOMENCLATURE_APPLI_SUITE_DONNEE,
								fournirCleNomNomenclatureAppliSuiteDonnee(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureAppliSuiteDonnee 
								= fournirNomNomenclatureAppliSuiteDonneeEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureAppliSuiteDonnee = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
									METHODE_GET_NOM_NOMENCLATURE_APPLI_SUITE_DONNEE,
								fournirCleNomNomenclatureAppliSuiteDonnee(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureAppliSuiteDonnee 
							= fournirNomNomenclatureAppliSuiteDonneeEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureAppliSuiteDonnee 
						= fournirNomNomenclatureAppliSuiteDonneeEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureAppliSuiteDonnee == null)._________

			return nomNomenclatureAppliSuiteDonnee;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureAppliSuiteDonnee().______________________


	
	/**
	 * method fournirCleNomNomenclatureAppliSuiteDonnee() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * de l'attribut suiteDonnee 
	 * dans l'application
	 * stockée dans application_fr_FR.properties.<br/>
	 * "Nomenclature_Suite_Donnee_UTF8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclature.appli.suitedonnee".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclature.appli.suitedonnee".<br/>
	 */
	private static String fournirCleNomNomenclatureAppliSuiteDonnee() {
		return "application.repertoire.ressources.nomenclature.appli.suitedonnee";
	} // Fin de fournirCleNomNomenclatureAppliSuiteDonnee()._______________
	

	
	/**
	 * method fournirNomNomenclatureAppliSuiteDonneeEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant l'attribut suiteDonnee
	 * dans l'application.<br/>
	 * <br/>
	 *
	 * @return : String : "Nomenclature_Suite_Donnee_UTF8.csv".<br/>
	 */
	private static String fournirNomNomenclatureAppliSuiteDonneeEnDur() {
		return "Nomenclature_Suite_Donnee_UTF8.csv";
	} // Fin de fournirNomNomenclatureAppliSuiteDonneeEnDur()._____________

	
	
	/**
	 * method getFichierNomenclatureAppliSuiteDonneeUtf8() :<br/>
	 * Fournit un Singleton du Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * l'attribut suiteDonnee
	 * dans l'application.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\appli\\
	 * Nomenclature_Suite_Donnee_UTF8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureAppliSuiteDonneeUtf8.<br/>
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureAppliSuiteDonneeUtf8() 
															throws Exception {
				
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureAppliSuiteDonneeUtf8 == null) {
								
				fichierNomenclatureAppliSuiteDonneeUtf8 
				= new File(getCheminNomenclaturesAppliUtf8() 
						+ getNomNomenclatureAppliSuiteDonnee());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureAppliSuiteDonneeUtf8
						, "Méthode getFichierNomenclatureAppliSuiteDonneeUtf8()");
			}
			
			return fichierNomenclatureAppliSuiteDonneeUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureAppliSuiteDonneeUtf8()._______________
	

	
	
	// ******************************************************************
	// Commun à toutes les nomenclatures de l'application. **************
	
	/**
	 * method getSetClesNomenclatureUtf8(
	 * File pNomenclature) :<br/>
	 * Retourne le Singleton de Set&lt;Integer&gt; des clés 
	 * d'une nomenclature en UTF8 pNomenclature.<br/>
	 * <br/>
	 *
	 * @param pNomenclature : File.<br/>
	 * @return : Set&lt;Integer&gt; : Ensemble des 
	 * clés d'une nomenclature en UTF8.<br/>
	 */
	public static Set<Integer> getSetClesNomenclatureUtf8(
			final File pNomenclature) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Jette RunTimeException si mauvais fichier. */
			traiterFichier(pNomenclature, METHODE_GETSETCLESNOMENCLATUREUTF8);
			
			/* typeErreur. */
			if (pNomenclature.equals(fichierNomenclatureAppliTypeErreurUtf8)) {
				return getSetClesNomenclatureUtf8(
						setClesNomenclatureTypeErreur
							, fichierNomenclatureAppliTypeErreurUtf8);
			}
			
			/* suiteDonnee. */
			if (pNomenclature.equals(fichierNomenclatureAppliSuiteDonneeUtf8)) {
				return getSetClesNomenclatureUtf8(
						setClesNomenclatureSuiteDonnee
							, fichierNomenclatureAppliSuiteDonneeUtf8);
			}
			return null;
			
		} // Fin de synchronized._____________________________________
			
	} // Fin de getSetClesNomenclatureUtf8(
	 // File pNomenclature)._______________________________________________
	
	
	
	/**
	 * method getSetClesNomenclatureUtf8(
	 * Set&lt;Integer&gt; pSet
	 * , File pNomenclature) :<br/>
	 * Retourne le Singleton pSet.<br/>
	 * Instancie et remplit pSet avec les valeurs des clés 
	 * contenues dans le fichier de nomenclature si pSet est null.
	 * <br/>
	 *
	 * @param pSet : Set&lt;Integer&gt;.<br/>
	 * @param pNomenclature : File.<br/>
	 * 
	 * @return : Set&lt;Integer&gt;.<br/>
	 */
	private static Set<Integer> getSetClesNomenclatureUtf8(
			Set<Integer> pSet, final File pNomenclature) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Jette RunTimeException si mauvais fichier. */
			traiterFichier(pNomenclature, METHODE_GETSETCLESNOMENCLATUREUTF8);
			
			/* Instanciation du Singleton. */
			if (pSet == null) {
				pSet = remplirSetClesNomenclatureUtf8(pNomenclature);
			}
			
			return pSet;
			
		} // Fin de synchronized._____________________________________
		
	} // Fin de getSetClesNomenclatureUtf8(
	 // Set<Integer> pSet
	 // , File pNomenclature)._____________________________________________

	
	
	/**
	 * method remplirSetClesNomenclatureUtf8(
	 * File pNomenclature) :<br/>
	 * <ul>
	 * <li>Remplit le Set&lt;Integer&gt; contenant 
	 * les clés Integer d'une nomenclature en UTF-8 
	 * en lisant le fichier UTF-8 de la nomenclature.</li><br/>
	 * <ul>
	 * <li>Saute les lignes d'en-tête de la nomenclature.</li><br/>
	 * <li>Saute les lignes blank dans la nomenclature.</li><br/>
	 * <li>Saute les éventuelles lignes vides 
	 * n'ayant pas 2 colonnes clé-valeur 
	 * remplies dans la nomenclature.</li><br/>
	 * </ul>
	 * </ul>
	 * <br/>
	 *
	 * @param pNomenclature : File : 
	 * Fichier csv (encodé en UTF-8) de type clé-valeur 
	 * contenant une nomenclature.<br/>
	 * 
	 * @return : Set&lt;Integer&gt; : Ensemble des clés Integer 
	 * d'une nomenclature.<br/>
	 */
	private static Set<Integer> remplirSetClesNomenclatureUtf8(
			final File pNomenclature) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Jette RunTimeException si mauvais fichier. */
			traiterFichier(pNomenclature
					, METHODE_REMPLIRSETCLESNOMENCLATUREUTF8);
			
			final Set<Integer> resultatSet = new HashSet<Integer>();
			
			/* Ouverture des flux. */
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader bfr = null;
			
			try {
				
				fis =  new FileInputStream(pNomenclature);
				isr = new InputStreamReader(fis, CHARSET_UTF8);
				bfr = new BufferedReader(isr);
				
				String ligneLue = null;
				
				// LECTURE DES LIGNES.**********************************
				while ((ligneLue = bfr.readLine()) != null) {
					
					/* Saute les éventuelles 
					 * lignes blank dans la nomenclature. */
					if (StringUtils.isBlank(ligneLue)) {
						continue;
					}
					
					
					/* Instancie un Pattern chargé de retrouver le 
					 * séparateur ';' dans la ligne. */
					final String[] tokens 
						= PATTERN_SEPARATEUR_CSV.split(ligneLue);
					
					/* Saute les éventuelles 
					 * lignes vides n'ayant pas 2 colonnes clé-valeur 
					 * remplies dans la nomenclature. */
					if (tokens.length != 2) {
						continue;
					}
					
					/* saute la ligne d'en-tête le cas échéant en se basant 
					 * sur le fait qu'on aura une String comme 
					 * 'clé' pour l'en-tête  
					 * et une valeur entière pour toutes 
					 * les lignes significatives. */
					final String cle = tokens[0];
					
					if (!StringUtils.isBlank(cle)) {
						try {
							Integer.parseInt(cle);
						} catch (NumberFormatException e) {
							continue;
						}
					}
					
					/* DECOMPOSITION DE CHAQUE LIGNE. */
					final Integer cleLue = Integer.parseInt(tokens[0]);
					
					// AJOUT DANS LE SET DES CLES POSSIBLES.____
					resultatSet.add(cleLue);
					
				} // FIN LECTURE DES LIGNES.******************************
				
				/* FERMETURE DES FLUX. */
				fis.close();
				isr.close();
				bfr.close();			
			
			} catch (FileNotFoundException fnfe) {
				
				fermerFlux(bfr, isr, fis);
				
				throw new RuntimeException(PROBLEME_GRAVE, fnfe);
				
			} catch (NumberFormatException nfe) {
				
				fermerFlux(bfr, isr, fis);
				
				throw new RuntimeException(PROBLEME_GRAVE, nfe);
				
			} catch (IOException ioe) {
				
				fermerFlux(bfr, isr, fis);
				
				throw new RuntimeException(PROBLEME_GRAVE, ioe);
				
			}
						
			return resultatSet;
			
		} // Fin de synchronized.______________________________________
		
	} // Fin de remplirSetClesNomenclatureUtf8(
	 // File pNomenclature)._______________________________________________
	

	
	/**
	 * method fermerFlux(
	 * BufferedReader pBfr
	 * , InputStreamReader pIsr
	 * , FileInputStream pFis) :<br/>
	 * Ferme les flux.<br/>
	 * <br/>
	 *
	 * @param pBfr : BufferedReader.<br/>
	 * @param pIsr : InputStreamReader.<br/>
	 * @param pFis : FileInputStream.<br/>
	 */
	private static void fermerFlux(
			final BufferedReader pBfr
				, final InputStreamReader pIsr
					, final FileInputStream pFis) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			if (pBfr != null) {
				try {
					pBfr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (pIsr != null) {
				try {
					pIsr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (pFis != null) {
				try {
					pFis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} // Fin de synchronized.______________________________________
		
	} // Fin de fermerFlux(
	 // BufferedReader pBfr
	 // , InputStreamReader pIsr
	 // , FileInputStream pFis).___________________________________________
	
	
	
	/**
	 * method getMapNomenclatureUtf8(
	 * File pNomenclature) :<br/>
	 * Retourne une nomenclature en UTF8 pNomenclature
	 * sous forme de SortedMap&lt;Integer, String&gt;.<br/>
	 * <br/>
	 *
	 * @param pNomenclature : File.<br/>
	 * @return : SortedMap&lt;Integer, String&gt; : 
	 * nomenclature en UTF8.<br/>
	 */
	public static SortedMap<Integer, String> getMapNomenclatureUtf8(
			final File pNomenclature) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Jette RunTimeException si mauvais fichier. */
			traiterFichier(pNomenclature
					, METHODE_GETMAPNOMENCLATUREUTF8);
			
			/* typeErreur. */
			if (pNomenclature.equals(fichierNomenclatureAppliTypeErreurUtf8)) {
				return getMapNomenclatureUtf8(
						mapNomenclatureTypeErreur
							, fichierNomenclatureAppliTypeErreurUtf8);
			}
			
			/* suiteDonnee. */
			if (pNomenclature.equals(fichierNomenclatureAppliSuiteDonneeUtf8)) {
				return getMapNomenclatureUtf8(
						mapNomenclatureSuiteDonnee
							, fichierNomenclatureAppliSuiteDonneeUtf8);
			}
			
			return null;
			
		} // Fin de synchronized._____________________________________
			
	} // Fin de getMapNomenclatureUtf8(
	 // File pNomenclature)._______________________________________________
	
	
	
	/**
	 * method getMapNomenclatureUtf8(
	 * SortedMap&lt;Integer, String&gt; pMap
	 * , File pNomenclature) :<br/>
	 * Retourne le Singleton pMap.<br/>
	 * Instancie et remplit pMap avec les valeurs 
	 * contenues dans le fichier de nomenclature si pMap est null.
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer, String&gt;.<br/>
	 * @param pNomenclature : File.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, String&gt;.<br/>
	 */
	private static SortedMap<Integer, String> getMapNomenclatureUtf8(
			SortedMap<Integer, String> pMap, final File pNomenclature) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Jette RunTimeException si mauvais fichier. */
			traiterFichier(pNomenclature
					, METHODE_GETMAPNOMENCLATUREUTF8);
			
			/* Instanciation du Singleton. */
			if (pMap == null) {
				pMap = remplirMapNomenclatureUtf8(pNomenclature);
			}
			
			return pMap;
			
		} // Fin de synchronized._____________________________________
		
	} // Fin de getMapNomenclatureUtf8(
	 // Map<Integer, String> pMap
	 // , File pNomenclature)._____________________________________________

	
	
	/**
	 * method remplirMapNomenclatureUtf8(
	 * File pNomenclature) :<br/>
	 * <ul>
	 * <li>Remplit la SortedMap&lt;Integer, String&gt; contenant 
	 * une nomenclature en UTF-8 (clé-valeur)
	 * en lisant le fichier UTF-8 de la nomenclature.</li><br/>
	 * <ul>
	 * <li>Saute les lignes d'en-tête de la nomenclature.</li><br/>
	 * <li>Saute les lignes blank dans la nomenclature.</li><br/>
	 * <li>Saute les éventuelles lignes vides 
	 * n'ayant pas 2 colonnes clé-valeur 
	 * remplies dans la nomenclature.</li><br/>
	 * </ul>
	 * </ul>
	 * <br/>
	 *
	 * @param pNomenclature : File : 
	 * Fichier csv (encodé en UTF-8) de type clé-valeur 
	 * contenant une nomenclature.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, String&gt; : Ensemble des clés Integer 
	 * et valeurs String d'une nomenclature.<br/>
	 */
	private static SortedMap<Integer, String> remplirMapNomenclatureUtf8(
			final File pNomenclature) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Jette RunTimeException si mauvais fichier. */
			traiterFichier(pNomenclature
					, METHODE_REMPLIRMAPNOMENCLATUREUTF8);
			
			final SortedMap<Integer, String> resultatMap 
				= new TreeMap<Integer, String>();
			
			/* Ouverture des flux. */
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader bfr = null;
			
			try {
				
				fis =  new FileInputStream(pNomenclature);
				isr = new InputStreamReader(fis, CHARSET_UTF8);
				bfr = new BufferedReader(isr);
				
				String ligneLue = null;
				
				// LECTURE DES LIGNES.**********************************
				while ((ligneLue = bfr.readLine()) != null) {
					
					/* Saute les éventuelles 
					 * lignes blank dans la nomenclature. */
					if (StringUtils.isBlank(ligneLue)) {
						continue;
					}
					
					/* Instancie un Pattern chargé de retrouver le 
					 * séparateur ';' dans la ligne. */
					final String[] tokens 
						= PATTERN_SEPARATEUR_CSV.split(ligneLue);
					
					/* Saute les éventuelles 
					 * lignes vides n'ayant pas 2 colonnes clé-valeur 
					 * remplies dans la nomenclature. */
					if (tokens.length != 2) {
						continue;
					}
					
					/* saute la ligne d'en-tête le cas échéant en se basant 
					 * sur le fait qu'on aura une String 
					 * comme 'clé' pour l'en-tête  
					 * et une valeur entière pour toutes 
					 * les lignes significatives. */
					final String cle = tokens[0];
					
					if (!StringUtils.isBlank(cle)) {
						try {
							Integer.parseInt(cle);
						} catch (NumberFormatException e) {
							continue;
						}
					}
					
					/* DECOMPOSITION DE CHAQUE LIGNE. */
					final Integer cleLue = Integer.parseInt(tokens[0]);
					final String libelleLu = tokens[1];
					
					// AJOUT DANS LA MAP RESULTAT._____
					resultatMap.put(cleLue, libelleLu);
					
				} // FIN LECTURE DES LIGNES.******************************
				
				/* FERMETURE DES FLUX. */
				fis.close();
				isr.close();
				bfr.close();			
			
			} catch (FileNotFoundException fnfe) {
				throw new RuntimeException(PROBLEME_GRAVE, fnfe);
			} catch (NumberFormatException nfe) {
				throw new RuntimeException(PROBLEME_GRAVE, nfe);
			} catch (IOException ioe) {
				throw new RuntimeException(PROBLEME_GRAVE, ioe);
			}
						
			return resultatMap;
			
		} // Fin de synchronized.______________________________________
		
	} // Fin de remplirMapNomenclatureUtf8(
	 // File pNomenclature)._______________________________________________
	

	
	/**
	 * method getRapportConfigurationCsv() :<br/>
	 * Getter du Rapport du chargement de la configuration au format csv.<br/>
	 * <br/>
	 * - Le rapport est null si il n'y a eu aucun 
	 * problème d'initialisation de l'application.<br/>
	 * <br/>
	 *
	 * @return rapportConfigurationCsv : String.<br/>
	 */
	public static String getRapportConfigurationCsv() {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			return rapportConfigurationCsv;
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getRapportConfigurationCsv().______________________________


	
	/**
	 * method getMessageIndividuelRapport() :<br/>
	 * Getter du Message pour le 
	 * Rapport du chargement de la configuration au format csv 
	 * généré par chaque méthode individuellement.<br/>
	 * <br/>
	 *
	 * @return messageIndividuelRapport : String.<br/>
	 */
	public static String getMessageIndividuelRapport() {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			return messageIndividuelRapport;
		} // Fin de synchronized.________________________________________
		
	} // Fin de getMessageIndividuelRapport()._____________________________


	
	/**
	 * method creerMessageManqueCle(
	 * String pMethode
	 * , String pCle
	 * , ResourceBundle pBundle) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si une clé est absente dans un ResourceBundle.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * "Classe ConfigurationApplicationManager 
	 * - Méthode getStatsActivees() 
	 * - La clé 'abstractdao.statsactivees' 
	 * n'existe pas dans messagestechniquesfr_FR.properties".<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pCle : String : Clé dans le ResourceBundle.<br/>
	 * @param pBundle : ResourceBundle.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageManqueCle(
			final String pMethode
			 	, final String pCle
			 		, final ResourceBundle pBundle) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATION_NOMENCLATURES_APPLI);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append("La clé '");
			stb.append(pCle);
			stb.append("' n'existe pas dans ");
			stb.append(pBundle.getBaseBundleName());
			stb.append("fr_FR.properties");
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageManqueCle(
	 // String pMethode
	 // , String pCle
	 // , ResourceBundle pBundle)._________________________________________
	

	
	/**
	 * method creerMessageManqueValeur(
	 * String pMethode
	 * , String pCle
	 * , ResourceBundle pBundle) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si une valeur en face d'une clé est absente 
	 * dans un ResourceBundle.<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pCle : String : Clé dans le ResourceBundle.<br/>
	 * @param pBundle : ResourceBundle.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageManqueValeur(
			final String pMethode
			 	, final String pCle
			 		, final ResourceBundle pBundle) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATION_NOMENCLATURES_APPLI);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append("La valeur associée à la clé '");
			stb.append(pCle);
			stb.append("' n'existe pas (null ou vide) dans ");
			stb.append(pBundle.getBaseBundleName());
			stb.append("fr_FR.properties");
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageManqueValeur(
	 // String pMethode
	 // , String pCle
	 // , ResourceBundle pBundle)._________________________________________


	
	/**
	 * method ajouterMessageAuRapportConfigurationCsv(
	 * String pMessage) :<br/>
	 * Rajoute le message pMessage au rapport 
	 * de chargement de la configuration au format csv (à la ligne).<br/>
	 * <br/>
	 * - Ne fait rien si pMessage est blank.<br/>
	 * - Ne Rajoute PAS l'en-tête (avec BOM_UTF-8) 
	 * pour le rapport de chargement de la configuration si nécessaire.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : Message à rajouter 
	 * au rapport de chargement de la configuration.<br/>
	 */
	private static void ajouterMessageAuRapportConfigurationCsv(
			final String pMessage) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Ne fait rien si pMessage est blank. */
			if (StringUtils.isBlank(pMessage)) {
				return;
			}
			
			final StringBuilder stb = new StringBuilder();
						
			/* Rajoute le message au rapport de 
			 * chargement de la configuration au format csv (à la ligne). */
			if (!StringUtils.isBlank(rapportConfigurationCsv)) {
				stb.append(rapportConfigurationCsv);
				stb.append(NEWLINE);
			}
			
			stb.append(pMessage);
			stb.append(SEP_PV);
			
			rapportConfigurationCsv = stb.toString();
			
		} // Fin de synchronized.________________________________________
			
	} // Fin de ajouterMessageAuRapportConfigurationCsv(
	 // String pMessage).__________________________________________________
	

	
	/**
	 * method traiterFichier(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 * <ul>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierNullRunTimeException si pFile est null.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierInexistantRunTimeException si pFile est inexistant.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierRepertoireRunTimeException si pFile est un répertoire.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierVideRunTimeException si pFile est vide.</li><br/>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : Nom de la méthode appelante.<br/>
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	private static void traiterFichier(
			final File pFile
				, final String pMethode) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;
			
			/* si pFile est null.*******/
			if (pFile == null) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est null"); 

				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierNullRunTimeException. */
				throw new FichierNullRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile == null).__________

			
			/* si pFile est inexistant. *******************/
			if (!pFile.exists()) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est inexistant : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierInexistantRunTimeException. */
				throw new FichierInexistantRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (!pFile.exists()).____________

			
			/* si pFile est un répertoire. *******************/
			if (pFile.isDirectory()) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est un répertoire : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierRepertoireRunTimeException. */
				throw new FichierRepertoireRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile.isDirectory()).__________

			
			/* si pFile est vide. ***********************/
			if (pFile.length() == 0) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est vide : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierVideRunTimeException. */
				throw new FichierVideRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile.length() == 0)._________________
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de traiterFichier(
	 // File pFile
	 // , String pMethode).________________________________________________
	

	
	/**
	 * method creerMessageMauvaisFichier(
	 * String pMethode
	 * , String pMessage) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si un Fichier de ressources (Description de fichier, nomenclature, ...) 
	 * est introuvable.<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pMessage : String : message ciconstancié 
	 * de la méthode appelante.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageMauvaisFichier(
				final String pMethode
					, final String pMessage) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesAppliManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATION_NOMENCLATURES_APPLI);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMessage);
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageMauvaisFichier(
	 // String pMethode
	// , String pMessage)._________________________________________________
	

	
} // FIN DE LA CLASSE ConfigurationNomenclaturesAppliManager.----------------
