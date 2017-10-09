package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.metier.ComparatorRG;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.metier.meteo.GestionnaireRGInterrogationMeteo;

/**
 * class GestionnaireRG :<br/>
 * Classe utilitaire chargée de gérer en un point unique 
 * l'implémentation des règles de gestion (RG) 
 * de toute l'application.<br/>
 * <ul>
 * <li>La méthode <b>afficherListeRGImplementeesCsv()</b> 
 * retourne une String pour l'affichage de toutes 
 * les RG implémentées dans l'application.</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 * // Récupération du listing des RG.<br/>
 * final String listingRG 
 * = GestionnaireRG.afficherListeRGImplementeesCsv();<br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Manager, Configurateur, Règle de Gestion, RG, <br/>
 * Trier Map, Sort Collection, <br/>
 * Bloc static, Ressources externes, hors classpath,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 26 août 2017
 *
 */
public final class GestionnaireRG {

	// ************************ATTRIBUTS************************************/

	
	/**
	 * RG_INTERRO_PAYS_01 : String :<br/>
	 * Règle de Gestion.<br/>
	 * "RG_INTERRO_PAYS_01 : le pays doit être renseigné".<br/>
	 */
	public static final String RG_INTERRO_PAYS_01 
		= "RG_INTERRO_PAYS_01 : le pays doit être renseigné";

	
	/**
	 * RG_INTERRO_PAYS_01_MESSAGE : String :<br/>
	 * Message à l'attention de l'utilisateur.<br/>
	 * "Le pays n'est pas renseigné (obligatoire)"<br/>
	 */
	public static final String RG_INTERRO_PAYS_01_MESSAGE 
		= "Le pays n'est pas renseigné (obligatoire)";
	
	
	/**
	 * RG_INTERRO_PAYS_02 : String :<br/>
	 * Règle de Gestion.<br/>
	 * "RG_INTERRO_PAYS_02 : 
	 * le pays ne doit contenir que des lettres".<br/>
	 */
	public static final String RG_INTERRO_PAYS_02 
		= "RG_INTERRO_PAYS_02 : "
				+ "le pays ne doit contenir que des lettres";

	
	/**
	 * RG_INTERRO_PAYS_02_MESSAGE : String :<br/>
	 * Message à l'attention de l'utilisateur.<br/>
	 * "le pays ne doit contenir que des lettres 
	 * (pas de chiffres)"<br/>
	 */
	public static final String RG_INTERRO_PAYS_02_MESSAGE 
		= "le pays ne doit contenir que des lettres "
				+ "(pas de chiffres)";

	
	/**
	 * RG_INTERRO_VILLE_03 : String :<br/>
	 * Règle de Gestion.<br/>
	 * "RG_INTERRO_VILLE_03 : la ville doit être renseignée".<br/>
	 */
	public static final String RG_INTERRO_VILLE_03 
		= "RG_INTERRO_VILLE_03 : la ville doit être renseignée";

	
	/**
	 * RG_INTERRO_VILLE_03_MESSAGE : String :<br/>
	 * Message à l'attention de l'utilisateur.<br/>
	 * "La ville n'est pas renseignée (obligatoire)"<br/>
	 */
	public static final String RG_INTERRO_VILLE_03_MESSAGE 
		= "La ville n'est pas renseignée (obligatoire)";
	
	
	/**
	 * RG_INTERRO_VILLE_04 : String :<br/>
	 * Règle de Gestion.<br/>
	 * "RG_INTERRO_VILLE_04 : la ville ne doit contenir que des lettres".<br/>
	 */
	public static final String RG_INTERRO_VILLE_04 
		= "RG_INTERRO_VILLE_04 : la ville ne doit contenir que des lettres";

	
	/**
	 * RG_INTERRO_VILLE_04_MESSAGE : String :<br/>
	 * Message à l'attention de l'utilisateur.<br/>
	 * "la ville ne doit contenir que des lettres 
	 * (pas de chiffres)"<br/>
	 */
	public static final String RG_INTERRO_VILLE_04_MESSAGE 
		= "la ville ne doit contenir que des lettres "
				+ "(pas de chiffres)";


	/**
	 * RG_INTERRO_NBREJOURS_05 : String :<br/>
	 * Règle de Gestion.<br/>
	 * "RG_INTERRO_NBREJOURS_05 : le nombre de jours doit être 
	 * homogène à un entier numérique".<br/>
	 */
	public static final String RG_INTERRO_NBREJOURS_05 
		= "RG_INTERRO_NBREJOURS_05 : le nombre de jours "
				+ "doit être homogène à un entier numérique";

	
	/**
	 * RG_INTERRO_NBREJOURS_05_MESSAGE : String :<br/>
	 * Message à l'attention de l'utilisateur.<br/>
	 * "le nombre de jours doit être doit être homogène 
	 * à un entier numérique".<br/>
	 */
	public static final String RG_INTERRO_NBREJOURS_05_MESSAGE 
		= "le nombre de jours doit être doit être homogène "
				+ "à un entier numérique";

	
	/**
	 * RG_INTERRO_NBREJOURS_06 : String :<br/>
	 * Règle de Gestion.<br/>
	 * "RG_INTERRO_NBREJOURS_06 : le nombre de jours 
	 * doit être inférieur ou égal à 10".<br/>
	 */
	public static final String RG_INTERRO_NBREJOURS_06 
		= "RG_INTERRO_NBREJOURS_06 : le nombre de jours "
				+ "doit être inférieur ou égal à 10";

	
	/**
	 * RG_INTERRO_NBREJOURS_06_MESSAGE : String :<br/>
	 * Message à l'attention de l'utilisateur.<br/>
	 * "le nombre de jours doit être inférieur ou égal à 10".<br/>
	 */
	public static final String RG_INTERRO_NBREJOURS_06_MESSAGE 
		= "le nombre de jours doit être inférieur ou égal à 10";

	
	/**
	 * VALIDEUR_INTERROGATIONMETEO : String :<br/>
	 * Nom de la classe Valideur chargée d'effectuer les 
	 * contrôles de validation des RG.<br/>
	 * "InterrogationMeteoValideur".<br/>
	 */
	public static final String VALIDEUR_INTERROGATIONMETEO 
		= "InterrogationMeteoValideur";
	
		
	/**
	 * INTERROGATIONMETEO : String :<br/>
	 * Objet métier (BEAN) concerné par la validation des RG.<br/>
	 * "InterrogationMeteoString".<br/>
	 */
	public static final String INTERROGATIONMETEO 
		= "InterrogationMeteoString";

	
	/**
	 * RG_PROPERTIES : String :<br/>
	 * fichier properties contenant les booleans d'activation 
	 * des contrôles des RG.<br/>
	 * "rg.properties".<br/>
	 */
	public static final String RG_PROPERTIES 
		= "rg.properties";
	
	
	/**
	 * SAUT_LIGNE : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE = '\n';
	
	
	/**
	 * bundleExterneRG : ResourceBundle :<br/>
	 * ResourceBundle encapsulant rg.properties.<br/>
	 * rg.properties est un fichier EXTERNE (hors classpath) 
	 * qui doit être accessible à la Maîtrise d'Ouvrage (MOA).<br/>
	 */
	private static ResourceBundle bundleExterneRG;
	
	
	/**
	 * mapRG : Map&lt;String,LigneRG&gt; :<br/>
	 * <ul>
	 * Map contenant toutes les RG implémentées dans l'application avec :
	 * <li>String : nom de la RG</li>
	 * <li>LigneRG : Encapsulation des éléments relatifs à la RG</li>
	 * </ul>
	 * Une ligne RG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;
	 * properties;clé;].<br/>
	 */
	private static Map<String, LigneRG> mapRG 
		= new ConcurrentHashMap<String, LigneRG>();

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(GestionnaireRG.class);
	
	
static {
	
	try {
		
		remplirMapRG();
		
	} catch (MalformedURLException malformedURLexc) {
		
		final String message 
			= "Impossible de fournir la liste des RG implémentées";
		
		if (LOG.isFatalEnabled()) {
			LOG.fatal(message, malformedURLexc);
		}
	}
}


	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GestionnaireRG() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private GestionnaireRG() {
		
		super();
				
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	


	/**
	 * method getBundleExterneRG() :<br/>
	 * Fournit le ResourceBundle associé au fichier externe rg.properties 
	 * avec la Locale Locale_fr_FR.<br/>
	 * <br/>
	 *
	 * @return : ResourceBundle : rg.properties.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static ResourceBundle getBundleExterneRG() 
			throws MalformedURLException {
		
		synchronized (GestionnaireRG.class) {
			
			if (bundleExterneRG == null) {
				bundleExterneRG = getBundleExterneRG(Locale.getDefault());
			}
			
			return bundleExterneRG;
			
		} // Fin de bloc synchronized.__________________________
				
	} // Fin de getBundleExterneRG().______________________________________
	
	
	
	/**
	 * method getBundleExterneRG(
	 * Locale pLocale) :<br/>
	 * Fournit le ResourceBundle associé au fichier externe rg.properties 
	 * avec la Locale pLocale.<br/>
	 * <br/>
	 *
	 * @param pLocale : Locale.<br/>
	 * 
	 * @return : ResourceBundle.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	private static ResourceBundle getBundleExterneRG(
			final Locale pLocale) throws MalformedURLException {
		
		synchronized (GestionnaireRG.class) {
			
			ResourceBundle resourceBundle = null;
							
			/* Accède au répertoire externe contenant rg.properties. */
			final File repertoireRessourcesParametrables 
			= new File(fournirCheminRessourceExterneRG());
							
			final URL[] urlsRessourcesParametrables 
			= {repertoireRessourcesParametrables.toURI().toURL()};
		
			final ClassLoader loaderRessourcesParametrables 
				= new URLClassLoader(urlsRessourcesParametrables);
			
			resourceBundle 
				= ResourceBundle
					.getBundle("rg"
							, pLocale
								, loaderRessourcesParametrables);
														
			return resourceBundle;
			
		} // Fin de bloc synchronized.__________________________
				
	} // Fin de getBundleExterneRG(...).___________________________________
	

	
	/**
	 * method fournirCheminRessourceExterneRG() :<br/>
	 * fournit le chemin (externe au projet) du répertoire 
	 * contenant le fichier rg.properties.<br/>
	 * "D:/Donnees/eclipse/eclipseworkspace_neon/
	 * tuto_maven_sonatype/ressources_externes".<br/>
	 * Ce chemin doit être écrit EN DUR 
	 * (surtout pas relatif au projet Eclipse).<br/>
	 * <br/>
	 *
	 * @return : String : "D:/Donnees/eclipse/eclipseworkspace_neon/
	 * tuto_maven_sonatype/ressources_externes".<br/>
	 */
	private static String fournirCheminRessourceExterneRG() {
					
		return "D:/Donnees/eclipse/eclipseworkspace_neon/"
				+ "tuto_maven_sonatype/ressources_externes";
					
	} // Fin de fournirCheminRessourceExterneRG()._________________________
	
	
	
	/**
	 * method remplirMapRG() :<br/>
	 * <ul>
	 * remplit et retourne la Map&lt;String, LigneRG&gt; mapRG 
	 * contenant toutes 
	 * les Règles de Gestion (RG) implémentées 
	 * dans les services de l'application avec :
	 * <li>String : le nom de la RG.</li>
	 * <li>LigneRG : pure fabrication encapsulant 
	 * tous les éléments relatifs à la RG.</li>
	 * </ul>
	 * Une LigneRG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;].<br/>
	 * <br/>
	 *
	 * @return : Map&lt;String, LigneRG&gt; : mapRG.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	private static Map<String, LigneRG> remplirMapRG() 
			throws MalformedURLException {
		
		synchronized (GestionnaireRG.class) {
			
			/* RG_INTERRO_PAYS_01. */
			final LigneRG ligneRgInterroPays01 
				= new LigneRG(getValiderPaysInterrogationMeteo()
						, getValiderRGInterroPays01()
						, RG_INTERRO_PAYS_01
						, 1
						, RG_INTERRO_PAYS_01_MESSAGE
						, INTERROGATIONMETEO, "Pays"
						, VALIDEUR_INTERROGATIONMETEO
						, "validerRGInterroPays01(...)"
						, RG_PROPERTIES
						, "interrogationmeteo.pays.rginterropays01.actif");
			
			mapRG.put(
					RG_INTERRO_PAYS_01
						, ligneRgInterroPays01);
			
			/* RG_INTERRO_PAYS_02. */
			final LigneRG ligneRgInterroPays02 
				= new LigneRG(getValiderPaysInterrogationMeteo()
						, getValiderRGInterroPays02()
						, RG_INTERRO_PAYS_02
						, 2
						, RG_INTERRO_PAYS_02_MESSAGE
						, INTERROGATIONMETEO, "Pays"
						, VALIDEUR_INTERROGATIONMETEO
						, "validerRGInterroPays02(...)"
						, RG_PROPERTIES
						, "interrogationmeteo.pays.rginterropays02.actif");
			
			mapRG.put(
					RG_INTERRO_PAYS_02
						, ligneRgInterroPays02);
			
			/* RG_INTERRO_VILLE_03. */
			final LigneRG ligneRgInterroVille03 
			= new LigneRG(getValiderVilleInterrogationMeteo()
					, getValiderRGInterroVille03()
					, RG_INTERRO_VILLE_03
					, 1
					, RG_INTERRO_VILLE_03_MESSAGE
					, INTERROGATIONMETEO, "Ville"
					, VALIDEUR_INTERROGATIONMETEO
					, "validerRGInterroVille03(...)"
					, RG_PROPERTIES
					, "interrogationmeteo.ville.rginterroville03.actif");
		
			mapRG.put(
					RG_INTERRO_VILLE_03
						, ligneRgInterroVille03);
			
			/* RG_INTERRO_VILLE_04. */
			final LigneRG ligneRgInterroVille04 
			= new LigneRG(getValiderVilleInterrogationMeteo()
					, getValiderRGInterroVille04()
					, RG_INTERRO_VILLE_04
					, 2
					, RG_INTERRO_VILLE_04_MESSAGE
					, INTERROGATIONMETEO, "Ville"
					, VALIDEUR_INTERROGATIONMETEO
					, "validerRGInterroVille04(...)"
					, RG_PROPERTIES
					, "interrogationmeteo.ville.rginterroville04.actif");
		
			mapRG.put(
					RG_INTERRO_VILLE_04
						, ligneRgInterroVille04);
			
			/* RG_INTERRO_NBREJOURS_05. */
			final LigneRG ligneRgInterroNbreJours05 
			= new LigneRG(getValiderNbreJoursInterrogationMeteo()
					, getValiderRGInterroNbreJours05()
					, RG_INTERRO_NBREJOURS_05
					, 2
					, RG_INTERRO_NBREJOURS_05_MESSAGE
					, INTERROGATIONMETEO, "nbreJours"
					, VALIDEUR_INTERROGATIONMETEO
					, "validerRGInterroNbreJours05(...)"
					, RG_PROPERTIES
					, "interrogationmeteo.nbrejours.rginterronbrejours05.actif");
		
			mapRG.put(
					RG_INTERRO_NBREJOURS_05
						, ligneRgInterroNbreJours05);

			/* RG_INTERRO_NBREJOURS_06. */
			final LigneRG ligneRgInterroNbreJours06 
			= new LigneRG(getValiderNbreJoursInterrogationMeteo()
					, getValiderRGInterroNbreJours06()
					, RG_INTERRO_NBREJOURS_06
					, 3
					, RG_INTERRO_NBREJOURS_06_MESSAGE
					, INTERROGATIONMETEO, "nbreJours"
					, VALIDEUR_INTERROGATIONMETEO
					, "validerRGInterroNbreJours06(...)"
					, RG_PROPERTIES
					, "interrogationmeteo.nbrejours.rginterronbrejours06.actif");
		
			mapRG.put(
					RG_INTERRO_NBREJOURS_06
						, ligneRgInterroNbreJours06);

			return mapRG;
			
		} // Fin de bloc synchronized.__________________________
		
	} // Fin de remplirMapRG().____________________________________________
	
	
	
	/**
	 * method getMapRG() :<br/>
	 * <ul>
	 * Getter de la Map contenant toutes les RG implémentées 
	 * dans l'application avec :
	 * <li>String : nom de la RG</li>
	 * <li>LigneRG : Encapsulation des éléments relatifs à la RG</li>
	 * </ul>
	 * Une LigneRG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;].<br/>
	 * <br/>
	 * Trie la Map en fonction du nom des Règles de Gestion (RG).<br/>
	 * <br/>
	 *
	 * @return : Map&lt;String,LigneRG&gt; : 
	 * Map contenant toutes les RG implémentées dans l'application.<br/>
	 */
	public static Map<String, LigneRG> getMapRG() {
		
		/* Tri de la Map en fonction du nom des Règles de Gestion.*/
		final SortedMap<String, LigneRG> mapTriee 
			= new TreeMap<String, LigneRG>(mapRG);
		
		return mapTriee;
		
	} // Fin de getMapRG().________________________________________________
	

	
	/**
	 * method getEnTeteCsv() :<br/>
	 * "id;Actif;activité des contrôles sur l'attribut;activité de la RG;
	 * RG implémentée;clé du type de contrôle;type de contrôle;Message d'erreur;
	 * Objet Métier concerné;Attribut concerné;Classe implémentant la RG;
	 * Méthode implémentant la RG;properties;clé;".<br/>
	 * <br/>
	 *
	 * @return : String : "id;Actif;
	 * activité des contrôles sur l'attribut;activité de la RG;
	 * RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;
	 * Objet Métier concerné;Attribut concerné;Classe implémentant la RG;
	 * Méthode implémentant la RG;properties;clé;".<br/>
	 */
	public static String getEnTeteCsv() {
		
		return "id;Actif;activité des contrôles sur l'attribut;"
				+ "activité de la RG;RG implémentée;clé du type de contrôle;"
				+ "type de contrôle;"
				+ "Message d'erreur;Objet Métier concerné;"
				+ "Attribut concerné;Classe implémentant la RG;"
				+ "Méthode implémentant la RG;properties;clé;";
		
	} // Fin de getEnTeteCsv().____________________________________________
	
	
	
	/**
	 * method afficherListeRGImplementeesCsv() :<br/>
	 * <ul>
	 * <li>Retourne une String pour l'affichage de la liste 
	 * des RG implémentées dans l'application.</li>
	 * <li>La String contient la liste des LignesRG au format csv.</li>
	 * </ul>
	 * Trie la Map en fonction du nom des Règles de Gestion (RG).<br/>
	 * <br/>
	 * retourne null si mapRG == null.<br/>
	 * <br/>
	 *
	 * @return : String : liste csv des RG implémentées.<br/>
	 */
	public static String afficherListeRGImplementeesCsv() {
		
		synchronized (GestionnaireRG.class) {
			
			/* retourne null si mapRG == null. */
			if (mapRG == null) {
				return null;
			}
			
			/* Tri de la Map en fonction du numéro des Règles de Gestion.*/
			/* Instanciation d'un comparateur de RG 
			 * qui trie sur les numéros des RG. */
			final ComparatorRG comparateurRG = new ComparatorRG();
			
			/* Instanciation d'une SortedMap vide avec le comparateur */
			final SortedMap<String, LigneRG> mapTriee 
				= new TreeMap<String, LigneRG>(comparateurRG);
			
			/* Remplissage de la map triée. */
			mapTriee.putAll(mapRG);
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(getEnTeteCsv());
			stb.append(SAUT_LIGNE);
			
			final Set<Entry<String, LigneRG>> entrySet 
				= mapTriee.entrySet();
			
			final Iterator<Entry<String, LigneRG>> ite 
				= entrySet.iterator();
			
			final int nbreEntry = entrySet.size();
			
			int compteur = 0;
			
			while (ite.hasNext()) {
				
				compteur++;
				
				final Entry<String, LigneRG> entry = ite.next();
				final LigneRG ligneRG = entry.getValue();
				
				stb.append(ligneRG.toStringCsv());
				
				if (compteur < nbreEntry) {
					stb.append(SAUT_LIGNE);
				}				
			}
			
			return stb.toString();
			
		} // Fin de bloc synchronized.__________________________
		
	} // Fin de afficherListeRGImplementeesCsv().__________________________
	
	
	
	/**
	 * method getLigneRG(
	 * String pNomRG) :<br/>
	 * Retourne l'encapsulation LigneRG correspondant 
	 * à la RG de nom pNomRG dans la mapRG.<br/>
	 * <br/>
	 * Une LigneRG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;
	 * properties;clé;].<br/>
	 * <br/>
	 *
	 * @param pNomRG : String : Nom de la Règle de Gestion (RG).<br/>
	 * 
	 * @return : LigneRG : pure fabrication.<br/>
	 * @throws MalformedURLException 
	 */
	public static LigneRG getLigneRG(
			final String pNomRG) throws MalformedURLException {
		
		return mapRG.get(pNomRG);
		
	} // Fin de getLigneRG(...).___________________________________________


	
	/**
	 * method getValiderPaysInterrogationMeteo() :<br/>
	 * Getter du Boolean activant globalement 
	 * les contrôles sur le Pays du InterrogationMeteo.<br/>
	 * <br/>
	 *
	 * @return validerPaysInterrogationMeteo : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderPaysInterrogationMeteo() 
				throws MalformedURLException {
		
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
					.getValiderPaysInterrogationMeteo();
		
	} // fin de getValiderPaysInterrogationMeteo().________________________


	
	/**
	 * method getValiderRGInterroPays01() :<br/>
	 * Getter du Boolean activant la RG-Interro-Pays-01 : 
	 * "le pays doit être renseigné".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroPays01 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroPays01() 
			throws MalformedURLException {
		
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
				.getValiderRGInterroPays01();
		
	} // Fin de getValiderRGClientNom01()._________________________________


	
	/**
	 * method getValiderRGInterroPays02() :<br/>
	 * Getter du Boolean activant la RG-Interro-Pays-02 : 
	 * "le pays ne doit contenir que des lettres".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroPays02 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroPays02() 
			throws MalformedURLException {
		
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
				.getValiderRGInterroPays02();
		
	} // Fin de getValiderRGInterroPays02()._______________________________


		
	/**
	 * method getValiderVilleInterrogationMeteo() :<br/>
	 * Getter du Boolean activant globalement 
	 * les contrôles sur la Ville du InterrogationMeteo.<br/>
	 * <br/>
	 *
	 * @return validerVilleInterrogationMeteo : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderVilleInterrogationMeteo() 
				throws MalformedURLException {
		
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
				.getValiderVilleInterrogationMeteo();
		
	} // Fin de getValiderVilleInterrogationMeteo()._______________________
	

	
	/**
	 * method getValiderRGInterroVille03() :<br/>
	 * Getter du Boolean activant la RG-Interro-Ville-03 : 
	 * "la Ville doit être renseignée".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroVille03 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroVille03() 
			throws MalformedURLException {
		
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
				.getValiderRGInterroVille03();
		
	} // Fin de getValiderRGInterroVille03().______________________________


		
	/**
	 * method getValiderRGInterroVille04() :<br/>
	 * Getter du Boolean activant la RG-Interro-Ville-04 : 
	 * "la Ville ne doit contenir que des lettres".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroVille04 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroVille04() 
			throws MalformedURLException {
		
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
				.getValiderRGInterroVille04();
		
	} // Fin de getValiderRGInterroVille04().______________________________


	
	/**
	 * method getValiderNbreJoursInterrogationMeteo() :<br/>
	 * Getter du Boolean activant globalement les contrôles 
	 * sur le NbreJours du InterrogationMeteo.<br/>
	 * <br/>
	 *
	 * @return validerNbreJoursInterrogationMeteo : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderNbreJoursInterrogationMeteo() 
			throws MalformedURLException {
	
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
				.getValiderNbreJoursInterrogationMeteo();
		
	} // Fin de getValiderNbreJoursInterrogationMeteo().___________________


	
	/**
	 * method getValiderRGInterroNbreJours05() :<br/>
	 * Getter du Boolean activant la RG_INTERRO_NBREJOURS_05 : 
	 * "le nombre de jours doit être homogène à un entier numérique".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroNbreJours05 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroNbreJours05() 
			throws MalformedURLException {
	
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
				.getValiderRGInterroNbreJours05();
		
	} // Fin de getValiderRGInterroNbreJours05().__________________________


	
	/**
	 * method getValiderRGInterroNbreJours06() :<br/>
	 * Getter du Boolean activant la RG_INTERRO_NBREJOURS_06 : 
	 * "le nombre de jours doit être inférieur ou égal à 10".<br/>
	 * <br/>
	 *
	 * @return validerRGInterroNbreJours06 : Boolean.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public static Boolean getValiderRGInterroNbreJours06() 
			throws MalformedURLException {
	
		/* Délégation au gestionnaire spécifique. */
		return GestionnaireRGInterrogationMeteo
				.getValiderRGInterroNbreJours06();
		
	} // Fin de getValiderRGInterroNbreJours06().__________________________


	
} // FIN DE LA CLASSE GestionnaireRG.----------------------------------------
